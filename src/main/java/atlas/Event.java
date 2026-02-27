package atlas;

import java.time.LocalDate;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " "
                + description + " (from: "
                + from + " to: " + to + ")";
    }

    @Override
    protected String getType() {
        return "";
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + from
                + " | " + to;
    }
}