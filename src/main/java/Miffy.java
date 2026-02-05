import java.util.Scanner;

/**
 * Main class for the Miffy task management program.
 * Handles user input and manages a list of ToDos, Event & Deadline.
 */
public class Miffy {

    /**
     * The main entry point of the program.
     * Prints the logo and starts handling user input.
     *
     */
    public static void main(String[] args) {
        String logo = " __  __ ___ _____ _____ __   __\n"
                + "|  \\/  |_ _|  ___|  ___| \\ \\ / /\n"
                + "| |\\/| || || |_  | |_   \\ \\ V / \n"
                + "| |  | || ||  _| |  _|   | | |  \n"
                + "|_|  |_|___|_|   |_|     |_|_| \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What are we doing now?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        handleUserInput(scanner);
    }

    /**
     * Handles user commands in a loop, including adding, listing,
     * marking, and unmarking tasks, as well as exiting the program.
     *
     * @param scanner The Scanner object used to read user input.
     */
    public static void handleUserInput(Scanner scanner) {
        Task[] tasks = new Task[100];
        int taskIndex;
        String taskDescription;
        String[] parts;
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            String command = input.split(" ")[0];

            switch (command) {
                case "list":
                    System.out.println("We are checking:");
                    printList(tasks, taskCount);
                    break;

                case "bye":
                    System.out.println("As always sir, a great pleasure watching you work!");
                    return;

                case "mark":
                    taskIndex = parseTaskIndex(input, 5);
                    tasks[taskIndex].markAsDone();
                    printMarkResult(tasks[taskIndex], true);
                    break;

                case "unmark":
                    taskIndex = parseTaskIndex(input, 7);
                    tasks[taskIndex].markAsNotDone();
                    printMarkResult(tasks[taskIndex], false);
                    break;

                case "todo":
                    taskDescription = input.substring(5);
                    tasks[taskCount] = new ToDo(taskDescription);
                    printInput(tasks[taskCount], taskCount + 1);
                    taskCount++;
                    break;

                case "deadline":
                    parts = input.substring(9).split("/by");
                    taskDescription = parts[0].trim();
                    String by = parts[1].trim();
                    tasks[taskCount] = new Deadline(taskDescription, by);
                    printInput(tasks[taskCount], taskCount + 1);
                    taskCount++;
                    break;

                case "event":
                    parts = input.substring(6).split("/from|/to");
                    taskDescription = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks[taskCount] = new Event(taskDescription, from, to);
                    printInput(tasks[taskCount], taskCount + 1);
                    taskCount++;
                    break;
            }
        }
    }

    /**
     * Parses the task index from user input for commands.
     *
     * @param input The user input string.
     * @param commandLength The length of the command prefix.
     * @return The zero-based index of the task.
     */
    private static int parseTaskIndex(String input, int commandLength) {
        return Integer.parseInt(input.substring(commandLength)) - 1;
    }

    /**
     * Prints the added task and the updated task count.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks.
     */
    public static void printInput(Task task, int taskCount) {
        System.out.println("Let's add that to the words of wisdom:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the list of all tasks.
     *
     * @param tasks Array of tasks.
     * @param taskCount Number of tasks in the list.
     */
    public static void printList(Task[] tasks, int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the result of marking or unmarking a task.
     *
     * @param task The task being updated.
     * @param isDone True if marking as done, false if unmarking.
     */
    private static void printMarkResult(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Congratulations on the task, it was such a success:");
        } else {
            System.out.println("Retired the task:");
        }

        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }
}
