import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws AtlasException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // first run: no file yet
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
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        throw new AtlasException("Corrupted data file.");
                }

                if (isDone) {
                    task.markDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new AtlasException("Error loading data file.");
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws AtlasException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // create ./data if needed

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

    private String encode(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + d.description + " | " + d.getBy();
        } else {
            Event e = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + e.description
                    + " | " + e.getFrom() + " | " + e.getTo();
        }
    }
}


