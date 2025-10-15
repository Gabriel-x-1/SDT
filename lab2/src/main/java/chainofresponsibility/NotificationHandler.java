package chainofresponsibility;

public abstract class NotificationHandler {
    protected NotificationHandler nextHandler;

    public void setNextHandler(NotificationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleEvent(SensorEvent event) {
        if (canHandle(event)) {
            processEvent(event);
        }

        if (nextHandler != null) {
            nextHandler.handleEvent(event);
        }
    }

    protected abstract boolean canHandle(SensorEvent event);
    protected abstract void processEvent(SensorEvent event);
}