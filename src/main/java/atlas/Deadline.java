package atlas;

import java.time.LocalDate;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " "
                + description + " (by: " + by + ")";
    }

    @Override
    protected String getType() {
        return "";
    }

    @Override
    public String toStorageString() {
        return "D | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + by;
    }
}