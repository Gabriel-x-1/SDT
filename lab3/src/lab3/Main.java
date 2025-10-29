package lab3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LoggerObserver logger = new LoggerObserver();
        DBConnectionPool pool = DBConnectionPool.getInstance(logger);

        int clientCount = 10;
        ExecutorService exec = Executors.newFixedThreadPool(clientCount);
        Random rnd = new Random();

        for (int i = 1; i <= clientCount; i++) {
            final int clientId = i;
            exec.submit(() -> {
                try {
                    DBConnection conn = pool.acquire(clientId);
                    Thread.sleep(500 + rnd.nextInt(1000));
                    pool.release(conn, clientId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            Thread.sleep(50);
        }

        exec.shutdown();
        exec.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("All clients finished. Created connections: " +
                pool.getCreatedCount() +
                " (max " + pool.getMaxConnections() + ")");
    }
}