package saving;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles persistent storage for tasks.
 *
 * <p>This class is responsible for:
 * <ul>
 *     <li>Ensuring the storage file exists.</li>
 *     <li>Loading tasks from disk into a TaskList.</li>
 *     <li>Saving tasks from a TaskList to disk.</li>
 * </ul>
 */
public class Storage {

    public static final String CURRENT_WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final Path FILE_PATH =
            Paths.get(CURRENT_WORKING_DIRECTORY, "data", "WordsOfWisdom.txt");

    private final File miffyFile;

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
     * Loads tasks from storage into a TaskList.
     *
     * @return TaskList containing parsed tasks.
     */
    public TaskList loadFromFile() {
        TaskList taskList = new TaskList();

        try (Scanner scanner = new Scanner(miffyFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                Task task = parseTask(line);
                if (task != null) {
                    taskList.addTaskToList(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Miffy did not find a file and is creating a new one.");
        }

        return taskList;
    }

    /**
     * Saves the tasks in a TaskList to storage.
     *
     * @param taskList TaskList to save.
     */
    public void saveToFile(TaskList taskList) {
        try (FileWriter writer = new FileWriter(miffyFile, false)) {
            for (Task task : taskList.getAllTasks()) {
                writer.write(task.toSavingString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Miffy could not save the data: " + e.getMessage());
        }
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
        boolean isDone = statusString.equals("true") || statusString.equals("1");
        String description = parts[2];

        Task task = switch (type) {
            case "T" -> parts.length == 3 ? new ToDo(description) : null;
            case "D" -> parts.length == 4 ? new Deadline(description, parts[3]) : null;
            case "E" -> parts.length == 5 ? new Event(description, parts[3], parts[4]) : null;
            default -> null;
        };

        if (task == null) {
            System.out.println("Miffy found an invalid task line: " + line);
            return null;
        }

        if (isDone) task.markAsDone();
        return task;
    }
}