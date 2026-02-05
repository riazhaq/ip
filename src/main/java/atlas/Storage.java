package atlas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     * If the file does not exist, returns an empty task list.
     */
    public ArrayList<Task> load() throws AtlasException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // first run: no data file yet
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                Task task;

                switch (type) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;

                    case "D":
                        task = new Deadline(
                                parts[2],
                                LocalDate.parse(parts[3])
                        );
                        break;

                    case "E":
                        task = new Event(
                                parts[2],
                                LocalDate.parse(parts[3]),
                                LocalDate.parse(parts[4])
                        );
                        break;

                    default:
                        throw new AtlasException("Corrupted data file.");
                }

                if (isDone) {
                    task.markDone();
                }

                tasks.add(task);
            }

        } catch (IOException | RuntimeException e) {
            // RuntimeException covers DateTimeParseException for corrupted files
            throw new AtlasException("Error loading data file.");
        }

        return tasks;
    }

    /**
     * Saves the current task list to the data file.
     */
    public void save(ArrayList<Task> tasks) throws AtlasException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // create ./data folder if needed

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (Task task : tasks) {
                    bw.write(encode(task));
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new AtlasException("Error saving data.");
        }
    }

    /**
     * Converts a task into a storable string format.
     * Dates are saved in ISO format (yyyy-mm-dd).
     */
    private String encode(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0")
                    + " | " + task.description;

        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0")
                    + " | " + d.description
                    + " | " + d.getBy(); // LocalDate -> yyyy-mm-dd

        } else {
            Event e = (Event) task;
            return "E | " + (task.isDone ? "1" : "0")
                    + " | " + e.description
                    + " | " + e.getFrom()
                    + " | " + e.getTo();
        }
    }
}
