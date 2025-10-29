package lab3;

public interface ConnectionObserver {
    void onAcquired(int connectionId, int clientId);
    void onReleased(int connectionId, int clientId);
}