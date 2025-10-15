package chainofresponsibility;

public class EmailNotification extends NotificationHandler {

    @Override
    protected boolean canHandle(SensorEvent event) {
        EventType type = event.getType();
        return type == EventType.FIRE ||
               type == EventType.INTRUSION ||
               type == EventType.WATER;
    }

    @Override
    protected void processEvent(SensorEvent event) {
        System.out.println("Email was sent for event (" + event + ")");
    }
}