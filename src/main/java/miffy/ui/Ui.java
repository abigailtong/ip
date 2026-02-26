package miffy.ui;

import miffy.task.Task;
import miffy.task.TaskList;

/**
 * Handles Miffy's user interface and prints messages in a friendly way.
 *
 * This class is responsible for displaying prompts, task lists,
 * task additions, deletions, status updates, errors, and
 * welcome/exit messages to the user.
 */
public class Ui {

    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    /**
     * Constructs a Ui instance.
     *
     * Currently, no initialization is required.
     */
    public Ui() {}

    /**
     * Displays a welcome message and Miffy logo.
     */
    public void showWelcome() {
        String logo = " __  __ ___ _____ _____ __   __\n"
                + "|  \\/  |_ _|  ___|  ___| \\ \\ / /\n"
                + "| |\\/| || || |_  | |_   \\ \\ V / \n"
                + "| |  | || ||  _| |  _|   | | |  \n"
                + "|_|  |_|___|_|   |_|     |_|_| \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What are we doing now?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the input prompt for the user.
     */
    public void showPrompt() {
        System.out.print("Your input: ");
    }

    /**
     * Displays an exit message and a horizontal line.
     */
    public void showExit() {
        System.out.println("As always sir, a great pleasure watching you work!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays an error message framed by horizontal lines.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Miffy says: " + message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message confirming that a task was added.
     *
     * @param task The task that was added.
     * @param tasks The current TaskList after addition.
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Let's add that to the words of wisdom:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message confirming that a task was deleted.
     *
     * @param task The task that was removed.
     * @param remainingCount The number of tasks remaining in the list.
     */
    public void showDeletedTask(Task task, int remainingCount) {
        System.out.println("Let's remove this from the words of wisdom:");
        System.out.println(" " + task);
        System.out.println("Now you have " + remainingCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message confirming that a task was marked as done.
     *
     * @param task The task that was marked done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Congratulations on the miffy.task, it was such a success:");
        System.out.println(" " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message confirming that a task was marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Retired the miffy.task:");
        System.out.println(" " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays all tasks in the TaskList with their indices.
     *
     * @param tasks The TaskList to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("We are checking:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays tasks that match a search keyword.
     *
     * @param foundTasks The list of tasks containing the keyword.
     * @param keyword The search keyword.
     */
    public void showFindResults(TaskList foundTasks, String keyword) {
        System.out.println("Miffy found these tasks containing \"" + keyword + "\":");

        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + foundTasks.getTask(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }
}