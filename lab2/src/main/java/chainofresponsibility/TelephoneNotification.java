package chainofresponsibility;

public class TelephoneNotification extends NotificationHandler {

    @Override
    protected boolean canHandle(SensorEvent event) {
        EventType type = event.getType();
        return type == EventType.FIRE || type == EventType.INTRUSION;
    }

    @Override
    protected void processEvent(SensorEvent event) {
        System.out.println("A call was made for event (" + event + ")");
    }
}