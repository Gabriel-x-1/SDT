package chainofresponsibility;

public class Logger extends NotificationHandler {

    @Override
    protected boolean canHandle(SensorEvent event) {
        return true;
    }

    @Override
    protected void processEvent(SensorEvent event) {
        System.out.println("Event (" + event + ") was logged");
    }
}