package calendar1.model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {

    private List<Event> events;

    public Calendar() {
        events = new ArrayList<>();
    }

    public Calendar(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
