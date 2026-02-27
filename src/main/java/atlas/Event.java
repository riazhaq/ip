package atlas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(fmt) + " to: " + to.format(fmt) + ")";
    }

    @Override
    protected String getType() { return "E"; }

    @Override
    public String toStorageString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Event other = (Event) obj;
        return Objects.equals(this.from, other.from) && Objects.equals(this.to, other.to);
    }
}