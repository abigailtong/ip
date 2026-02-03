import java.util.Scanner;
public class Miffy {
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

    public static void handleUserInput(Scanner scanner) {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            String command = input.split(" ")[0];

            switch(command) {
                case "list": {
                    System.out.println("We are checking:");
                    printList(tasks, taskCount);
                    break;
                }

                case "bye": {
                    System.out.println("As always sir, a great pleasure watching you work!");
                    return;
                }

                case "mark": {
                    int taskIndex = parseTaskIndex(input, 5);
                    tasks[taskIndex].markAsDone();
                    printMarkResult(tasks[taskIndex], true);
                    break;
                }

                case "unmark": {
                    int taskIndex = parseTaskIndex(input, 7);
                    tasks[taskIndex].markAsNotDone();
                    printMarkResult(tasks[taskIndex], false);
                    break;
                }

                case "todo": {
                    String description = input.substring(5);
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    printInput(tasks[taskCount - 1], taskCount);
                    break;
                }

                case "deadline": {
                    String[] parts = input.substring(9).split("/by");
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks[taskCount++] = new Deadline(description, by);
                    printInput(tasks[taskCount - 1], taskCount);
                    break;
                }

                case "event": {
                    String[] parts = input.substring(6).split("/from|/to");
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks[taskCount++] = new Event(description, from, to);
                    printInput(tasks[taskCount - 1], taskCount);
                    break;
                }

            }
        }
    }

    private static int parseTaskIndex(String input, int commandLength) {
        return Integer.parseInt(input.substring(commandLength)) - 1;
    }

    public static void printInput(Task task, int taskCount) {
        System.out.println("Let's add that to the words of wisdom:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printList(Task[] tasks, int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

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
