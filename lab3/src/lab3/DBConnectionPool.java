package lab3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Singleton Object Pool for DBConnections
 * Manages max 5 connections
 */
public class DBConnectionPool {
    private static final int MAX_CONNECTIONS = 5;
    private static DBConnectionPool instance;
    
    private final Deque<DBConnection> available = new ArrayDeque<>();
    private final Set<DBConnection> inUse = new HashSet<>();
    private final ConnectionObserver loggerObserver;
    private int createdCount = 0;

    // Private constructor for Singleton
    private DBConnectionPool(ConnectionObserver logger) {
        this.loggerObserver = logger;
    }

    public static synchronized DBConnectionPool getInstance(ConnectionObserver logger) {
        if (instance == null) {
            instance = new DBConnectionPool(logger);
        }
        return instance;
    }

    public DBConnection acquire(int clientId) throws InterruptedException {
        synchronized (this) {
            while (true) {
                // Try to reuse available connection
                if (!available.isEmpty()) {
                    DBConnection conn = available.removeFirst();
                    inUse.add(conn);
                    conn.acquiredBy(clientId);
                    return conn;
                }

                // Create new connection if under limit
                if (createdCount < MAX_CONNECTIONS) {
                    createdCount++;
                    DBConnection conn = new DBConnection(createdCount);
                    conn.addObserver(loggerObserver);
                    inUse.add(conn);
                    conn.acquiredBy(clientId);
                    return conn;
                }
                
                // Wait for a connection to be released
                this.wait();
            }
        }
    }

    public void release(DBConnection conn, int clientId) {
        synchronized (this) {
            if (!inUse.remove(conn)) {
                throw new IllegalArgumentException("Connection not in use: " + conn);
            }
            conn.releasedBy(clientId);
            available.addLast(conn);
            this.notifyAll();
        }
    }

    public synchronized int availableCount() {
        return available.size();
    }

    public synchronized int inUseCount() {
        return inUse.size();
    }
    
    public synchronized int getCreatedCount() {
        return createdCount;
    }
    
    public int getMaxConnections() {
        return MAX_CONNECTIONS;
    }
}