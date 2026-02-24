package saving;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistent storage for tasks.
 *
 * <p>This class is responsible for:
 * <ul>
 *     <li>Ensuring the storage file exists.</li>
 *     <li>Loading tasks from disk.</li>
 *     <li>Saving tasks to disk.</li>
 * </ul>
 *
 * <p>Storage format:
 * TYPE | STATUS | DESCRIPTION | [EXTRA_FIELDS]
 */
public class Storage {

    public static final String CURRENT_WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final Path FILE_PATH =
            Paths.get(CURRENT_WORKING_DIRECTORY, "data", "WordsOfWisdom.txt");

    private final File miffyFile;

    /**
     * Creates the storage manager and ensures required file structure exists.
     */
    public Storage() {
        this.miffyFile = FILE_PATH.toFile();
        initialiseStorage();
    }

    /**
     * Ensures storage directory and file exist.
     */
    private void initialiseStorage() {
        try {
            File parentDir = miffyFile.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!miffyFile.exists()) {
                miffyFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Miffy found an error when creating the file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * <p>Invalid lines are skipped and reported.</p>
     *
     * @return list of parsed tasks (empty if none)
     */
    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();

        try (Scanner scanner = new Scanner(miffyFile)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                Task task = parseTask(line);

                if (task != null) {
                    taskList.add(task);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Miffy did not find a file and is creating a new one.");
        }

        System.out.println("____________________________________________________________");
        return taskList;
    }

    /**
     * Parses a single line from storage into a Task.
     *
     * @param line raw storage line
     * @return parsed Task or null if invalid
     */
    private Task parseTask(String line) {

        String[] parts = line.split("\\s*\\|\\s*");

        if (parts.length < 3) {
            System.out.println("Miffy found an incorrect line (too short): " + line);
            return null;
        }

        String type = parts[0];
        String statusString = parts[1];

        if (!isValidStatus(statusString)) {
            System.out.println("Miffy found an incorrect line with an invalid status: " + line);
            return null;
        }

        boolean isDone = statusString.equals("true") || statusString.equals("1");
        String description = parts[2];

        Task task = createTask(type, parts, description, line);

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Validates task completion status string.
     */
    private boolean isValidStatus(String status) {
        return status.equals("true")
                || status.equals("false")
                || status.equals("1")
                || status.equals("0");
    }

    /**
     * Creates a task object based on type and fields.
     */
    private Task createTask(String type, String[] parts, String description, String originalLine) {

        switch (type) {
            case "T":
                if (parts.length == 3) {
                    return new ToDo(description);
                }
                System.out.println("Miffy found an incorrect ToDo: " + originalLine);
                return null;

            case "D":
                if (parts.length == 4) {
                    return new Deadline(description, parts[3]);
                }
                System.out.println("Miffy found an incorrect Deadline: " + originalLine);
                return null;

            case "E":
                if (parts.length == 5) {
                    return new Event(description, parts[3], parts[4]);
                }
                System.out.println("Miffy found an incorrect Event: " + originalLine);
                return null;

            default:
                System.out.println("Miffy found an incorrect task type: " + originalLine);
                return null;
        }
    }

    /**
     * Saves tasks to storage file.
     *
     * <p>Existing contents are overwritten.</p>
     *
     * @param taskList tasks to save
     */
    public void saveToFile(ArrayList<Task> taskList) {

        try (FileWriter writer = new FileWriter(miffyFile, false)) {

            for (Task task : taskList) {
                writer.write(task.toSavingString() + "\n");
            }

        } catch (IOException e) {
            System.out.println("Miffy could not save the data: " + e.getMessage());
        }
    }
}
