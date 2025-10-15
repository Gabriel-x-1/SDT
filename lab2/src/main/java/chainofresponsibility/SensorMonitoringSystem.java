package chainofresponsibility;

import java.util.Scanner;

public class SensorMonitoringSystem {

    public static void main(String[] args) {
        System.out.println("=== Sensor Monitoring System ===");
        System.out.println("Setting up Chain of Responsibility...");

        NotificationHandler emailHandler = new EmailNotification();
        NotificationHandler telephoneHandler = new TelephoneNotification();
        NotificationHandler logger = new Logger();

        emailHandler.setNextHandler(telephoneHandler);
        telephoneHandler.setNextHandler(logger);

        System.out.println("Handler chain established:");
        System.out.println("EmailNotification -> TelephoneNotification -> Logger");
        System.out.println("\nHandler responsibilities:");
        System.out.println("- EmailNotification: Fire, Intrusion, Water events");
        System.out.println("- TelephoneNotification: Fire, Intrusion events only");
        System.out.println("- Logger: All events");

        SensorEventGenerator generator = new SensorEventGenerator(emailHandler);

        System.out.println("\nStarting sensor event generation...");
        System.out.println("Press Enter to stop the system.\n");

        generator.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println("\nStopping sensor event generation...");
        generator.stopGeneration();

        try {
            generator.join(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("System shutdown complete.");
        scanner.close();
    }
}