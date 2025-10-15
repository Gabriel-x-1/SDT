package chainofresponsibility;

import java.time.LocalDateTime;
import java.util.Random;

public class SensorEventGenerator extends Thread {
    private NotificationHandler handlerChain;
    private volatile boolean running = true;
    private Random random = new Random();
    private String[] locations = {
        "Kitchen", "Living Room", "Bedroom", "Bathroom",
        "Garage", "Basement", "Attic", "Office"
    };

    public SensorEventGenerator(NotificationHandler handlerChain) {
        this.handlerChain = handlerChain;
    }

    @Override
    public void run() {
        while (running) {
            try {
                SensorEvent event = generateRandomEvent();
                System.out.println("\n--- New Event Generated ---");
                handlerChain.handleEvent(event);
                System.out.println("--- Event Processing Complete ---\n");

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private SensorEvent generateRandomEvent() {
        EventType[] eventTypes = EventType.values();
        EventType randomType = eventTypes[random.nextInt(eventTypes.length)];
        String randomLocation = locations[random.nextInt(locations.length)];
        LocalDateTime timestamp = LocalDateTime.now();

        return new SensorEvent(randomType, timestamp, randomLocation);
    }

    public void stopGeneration() {
        running = false;
        this.interrupt();
    }
}