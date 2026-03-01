package atlas;

/**
 * Represents a simple task without a specific date or time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    protected String getType() {
        return "T";
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}