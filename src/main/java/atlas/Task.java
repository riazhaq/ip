package atlas;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    protected abstract String getType();

    public abstract String toStorageString();

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Converts a storage line back into a Task object.
     */
    public static Task fromStorageString(String line) {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean done = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description,
                        java.time.LocalDate.parse(parts[3]));
                break;
            case "E":
                task = new Event(description,
                        java.time.LocalDate.parse(parts[3]),
                        java.time.LocalDate.parse(parts[4]));
                break;
            default:
                return null;
        }

        task.setDone(done);
        return task;
    }
}