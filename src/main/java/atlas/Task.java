package atlas;

import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) { this.isDone = done; }
    public String getDescription() { return description; }
    public String getStatusIcon() { return isDone ? "[X]" : "[ ]"; }

    protected abstract String getType();
    public abstract String toStorageString();

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Converts a storage line back into a Task object.
     * Skips corrupted lines or "null" dates to prevent startup crashes.
     */
    public static Task fromStorageString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) return null;

            String type = parts[0];
            boolean done = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    // If date is "null" or missing, skip this task
                    if (parts.length < 4 || parts[3].equals("null")) return null;
                    task = new Deadline(description, java.time.LocalDate.parse(parts[3]));
                    break;
                case "E":
                    // If either date is "null" or missing, skip this task
                    if (parts.length < 5 || parts[3].equals("null") || parts[4].equals("null")) return null;
                    task = new Event(description, java.time.LocalDate.parse(parts[3]),
                            java.time.LocalDate.parse(parts[4]));
                    break;
                default:
                    return null;
            }
            task.setDone(done);
            return task;
        } catch (Exception e) {
            // Log the error but don't crash the app
            System.err.println("Skipping corrupted line: " + line);
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        Task other = (Task) obj;
        return this.description.equalsIgnoreCase(other.description)
                && this.getType().equals(other.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description.toLowerCase(), getType());
    }
}