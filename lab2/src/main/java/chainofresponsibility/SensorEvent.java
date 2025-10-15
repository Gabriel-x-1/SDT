package chainofresponsibility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SensorEvent {
    private EventType type;
    private LocalDateTime timestamp;
    private String location;

    public SensorEvent(EventType type, LocalDateTime timestamp, String location) {
        this.type = type;
        this.timestamp = timestamp;
        this.location = location;
    }

    public EventType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s event at %s on %s",
                           type, location, timestamp.format(formatter));
    }
}