package atlas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task that has a specific deadline date.
 * A Deadline task includes a description and a {@code LocalDate} by which the task must be completed.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a new Deadline task.
     * * @param description A brief summary of the task.
     * @param by The date the task is due.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its status,
     * description, and formatted due date.
     * * @return A formatted string suitable for display in the GUI.
     */
    @Override
    public String toString() {
        String dateStr = (by == null) ? "no date" : by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + dateStr + ")";
    }

    /**
     * Returns the character code representing the type of this task.
     * * @return "D" for Deadline tasks.
     */
    @Override
    protected String getType() { return "D"; }

    /**
     * Formats the Deadline task into a string suitable for file storage.
     * The format used is: {@code D | isDone | description | by}.
     * * @return A pipe-delimited string representing the task in storage.
     */
    @Override
    public String toStorageString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Compares this deadline with another object for equality.
     * Two deadlines are considered equal if they have the same description and the same due date.
     * * @param obj The object to compare with.
     * @return {@code true} if the tasks are identical; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Deadline other = (Deadline) obj;
        return Objects.equals(this.by, other.by);
    }
}