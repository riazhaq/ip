package atlas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task that occurs over a specific time frame.
 * An Event task includes a description, a start date, and an end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new Event task.
     *
     * @param description A summary of the event.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its status,
     * description, and formatted date range.
     *
     * @return A formatted string for display in the task list.
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(fmt) + " to: " + to.format(fmt) + ")";
    }

    /**
     * Returns the character code representing the type of this task.
     *
     * @return "E" for Event tasks.
     */
    @Override
    protected String getType() { return "E"; }

    /**
     * Formats the Event task into a string suitable for file storage.
     * The format used is: {@code E | isDone | description | from | to}.
     *
     * @return A pipe-delimited string representing the event in storage.
     */
    @Override
    public String toStorageString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Compares this event with another object for equality.
     * Two events are considered equal if they have the same description, start date, and end date.
     *
     * @param obj The object to compare with.
     * @return {@code true} if the events are identical; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Event other = (Event) obj;
        return Objects.equals(this.from, other.from) && Objects.equals(this.to, other.to);
    }
}