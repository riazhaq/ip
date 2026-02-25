package atlas;

/**
 * Represents a task without a date or time.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    protected String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0")
                + " | " + description;
    }
}