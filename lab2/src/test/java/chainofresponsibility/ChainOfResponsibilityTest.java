package chainofresponsibility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class ChainOfResponsibilityTest {

    private NotificationHandler emailHandler;
    private NotificationHandler telephoneHandler;
    private NotificationHandler logger;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        emailHandler = new EmailNotification();
        telephoneHandler = new TelephoneNotification();
        logger = new Logger();

        emailHandler.setNextHandler(telephoneHandler);
        telephoneHandler.setNextHandler(logger);

        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testFireEventHandling() {
        SensorEvent fireEvent = new SensorEvent(EventType.FIRE, LocalDateTime.now(), "Kitchen");
        emailHandler.handleEvent(fireEvent);

        String output = outputStream.toString();
        assertTrue(output.contains("Email was sent for event"));
        assertTrue(output.contains("A call was made for event"));
        assertTrue(output.contains("was logged"));
        assertTrue(output.contains("FIRE"));
        assertTrue(output.contains("Kitchen"));
    }

    @Test
    public void testIntrusionEventHandling() {
        SensorEvent intrusionEvent = new SensorEvent(EventType.INTRUSION, LocalDateTime.now(), "Front Door");
        emailHandler.handleEvent(intrusionEvent);

        String output = outputStream.toString();
        assertTrue(output.contains("Email was sent for event"));
        assertTrue(output.contains("A call was made for event"));
        assertTrue(output.contains("was logged"));
        assertTrue(output.contains("INTRUSION"));
    }

    @Test
    public void testWaterEventHandling() {
        SensorEvent waterEvent = new SensorEvent(EventType.WATER, LocalDateTime.now(), "Basement");
        emailHandler.handleEvent(waterEvent);

        String output = outputStream.toString();
        assertTrue(output.contains("Email was sent for event"));
        assertFalse(output.contains("A call was made for event"));
        assertTrue(output.contains("was logged"));
        assertTrue(output.contains("WATER"));
    }

    @Test
    public void testTemperatureEventHandling() {
        SensorEvent tempEvent = new SensorEvent(EventType.TEMPERATURE, LocalDateTime.now(), "Living Room");
        emailHandler.handleEvent(tempEvent);

        String output = outputStream.toString();
        assertFalse(output.contains("Email was sent for event"));
        assertFalse(output.contains("A call was made for event"));
        assertTrue(output.contains("was logged"));
        assertTrue(output.contains("TEMPERATURE"));
    }

    @Test
    public void testSensorEventCreation() {
        LocalDateTime now = LocalDateTime.now();
        SensorEvent event = new SensorEvent(EventType.FIRE, now, "Kitchen");

        assertEquals(EventType.FIRE, event.getType());
        assertEquals(now, event.getTimestamp());
        assertEquals("Kitchen", event.getLocation());
        assertTrue(event.toString().contains("FIRE"));
        assertTrue(event.toString().contains("Kitchen"));
    }

    @Test
    public void testChainModification() {
        NotificationHandler customHandler = new NotificationHandler() {
            @Override
            protected boolean canHandle(SensorEvent event) {
                return event.getType() == EventType.TEMPERATURE;
            }

            @Override
            protected void processEvent(SensorEvent event) {
                System.out.println("Custom handler processed temperature event");
            }
        };

        customHandler.setNextHandler(logger);
        telephoneHandler.setNextHandler(customHandler);

        SensorEvent tempEvent = new SensorEvent(EventType.TEMPERATURE, LocalDateTime.now(), "Attic");
        emailHandler.handleEvent(tempEvent);

        String output = outputStream.toString();
        assertTrue(output.contains("Custom handler processed temperature event"));
        assertTrue(output.contains("was logged"));
    }

    @Test
    public void testEventTypes() {
        assertEquals(4, EventType.values().length);
        assertTrue(java.util.Arrays.asList(EventType.values()).contains(EventType.FIRE));
        assertTrue(java.util.Arrays.asList(EventType.values()).contains(EventType.INTRUSION));
        assertTrue(java.util.Arrays.asList(EventType.values()).contains(EventType.WATER));
        assertTrue(java.util.Arrays.asList(EventType.values()).contains(EventType.TEMPERATURE));
    }
}