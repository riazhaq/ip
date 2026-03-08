package atlas;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks.
 * Uses an absolute path logic to ensure the data folder exists relative to the execution directory.
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Ensures the directory exists and loads the tasks.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File directory = file.getParentFile();

        // 1. Explicitly check and create directory if it exists and is missing
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // 2. Create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }

        // 3. Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromStorageString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks, ensuring the directory exists first.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();

        // Ensure directory exists before attempting to write
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toStorageString());
                writer.newLine();
            }
        }
    }
}