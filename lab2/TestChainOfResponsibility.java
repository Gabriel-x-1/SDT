import chainofresponsibility.*;
import java.time.LocalDateTime;

public class TestChainOfResponsibility {
    public static void main(String[] args) {
        System.out.println("=== Exercise 3: Chain of Responsibility Pattern Demo ===");

        // Set up the chain of handlers
        NotificationHandler emailHandler = new EmailNotification();
        NotificationHandler telephoneHandler = new TelephoneNotification();
        NotificationHandler logger = new Logger();

        emailHandler.setNextHandler(telephoneHandler);
        telephoneHandler.setNextHandler(logger);

        System.out.println("Handler chain: EmailNotification -> TelephoneNotification -> Logger\n");

        // Test different event types
        SensorEvent fireEvent = new SensorEvent(EventType.FIRE, LocalDateTime.now(), "Kitchen");
        System.out.println("Testing FIRE event:");
        emailHandler.handleEvent(fireEvent);

        System.out.println("\nTesting INTRUSION event:");
        SensorEvent intrusionEvent = new SensorEvent(EventType.INTRUSION, LocalDateTime.now(), "Front Door");
        emailHandler.handleEvent(intrusionEvent);

        System.out.println("\nTesting WATER event:");
        SensorEvent waterEvent = new SensorEvent(EventType.WATER, LocalDateTime.now(), "Basement");
        emailHandler.handleEvent(waterEvent);

        System.out.println("\nTesting TEMPERATURE event:");
        SensorEvent tempEvent = new SensorEvent(EventType.TEMPERATURE, LocalDateTime.now(), "Living Room");
        emailHandler.handleEvent(tempEvent);

        System.out.println("\nChain of Responsibility Pattern test completed successfully!");
        System.out.println("Note: Full system with continuous thread generation can be run via SensorMonitoringSystem.main()");
    }
}