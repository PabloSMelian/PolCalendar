package calendar1.model;

import java.time.LocalDateTime;

public class Event {
    private final String name;
    private final LocalDateTime startInstant;
    private final int duration;
    private final int recordatory; //poner los minutos antes del evento pal recordatorio

    public Event(String name, LocalDateTime startInstant, int duration, int recordatory) {
        this.name = name;
        this.startInstant = startInstant;
        this.duration = duration;
        this.recordatory = recordatory;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartInstant() {
        return startInstant;
    }

    public int getDuration() {
        return duration;
    }

    public int getRecordatory() {
        return recordatory;
    }
}
