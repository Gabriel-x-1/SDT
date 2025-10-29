package lab3;


public class LoggerObserver implements ConnectionObserver {
    @Override
    public void onAcquired(int connectionId, int clientId) {
        System.out.printf("Connection %d was acquired by client %d%n", connectionId, clientId);
    }

    @Override
    public void onReleased(int connectionId, int clientId) {
        System.out.printf("Connection %d was released by client %d%n", connectionId, clientId);
    }
}