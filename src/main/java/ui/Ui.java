package ui;

import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;
import task.TaskList;

/**
 * Handles Miffy's user interface and printing messages in a friendly way.
 */
public class Ui {

    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    public Ui() {}

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

    public void showPrompt() {
        System.out.print("Your input: ");
    }

    public void showExit() {
        System.out.println("As always sir, a great pleasure watching you work!");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showError(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Miffy says: " + message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Let's add that to the words of wisdom:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showDeletedTask(Task task, int remainingCount) {
        System.out.println("Let's remove this from the words of wisdom:");
        System.out.println(" " + task);
        System.out.println("Now you have " + remainingCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTaskMarked(Task task) {
        System.out.println("Congratulations on the task, it was such a success:");
        System.out.println(" " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Retired the task:");
        System.out.println(" " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("We are checking:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }
}