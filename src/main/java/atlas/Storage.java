package atlas;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks to a text file on the hard drive.
 * This class ensures that the user's task list persists across application sessions.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with a specified file path.
     * @param filePath The relative path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the data file.
     * If the file or directory does not exist, they are created.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If there is an error reading or creating the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            Task task = Task.fromStorageString(line);
            if (task != null) {
                tasks.add(task);
            }
        }

        reader.close();
        return tasks;
    }

    /**
     * Saves the current list of tasks to the data file.
     *
     * @param tasks The list of tasks to be written to storage.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Task task : tasks) {
            writer.write(task.toStorageString());
            writer.newLine();
        }

        writer.close();
    }


}