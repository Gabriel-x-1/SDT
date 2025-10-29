package lab3;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DBConnection {
    private final int id;
    private final List<ConnectionObserver> observers = new CopyOnWriteArrayList<>();

    public DBConnection(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addObserver(ConnectionObserver o) {
        observers.add(o);
    }

    public void removeObserver(ConnectionObserver o) {
        observers.remove(o);
    }


    public void acquiredBy(int clientId) {
        for (ConnectionObserver o : observers) {
            o.onAcquired(id, clientId);
        }
    }

    public void releasedBy(int clientId) {
        for (ConnectionObserver o : observers) {
            o.onReleased(id, clientId);
        }
    }

    @Override
    public String toString() {
        return "Connection " + id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DBConnection)) return false;
        return ((DBConnection) o).id == this.id;
    }
}