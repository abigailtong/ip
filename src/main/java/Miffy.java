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

            if (input.equalsIgnoreCase("list")) {
                System.out.println("We are checking:");
                printList(tasks, taskCount);
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println("As always sir, a great pleasure watching you work!");
                break;
            } else if (input.startsWith("mark")) {
                int taskIndex = parseTaskIndex(input, 5);
                tasks[taskIndex].markAsDone();
                printMarkResult(tasks[taskIndex], true);
            }  else if (input.startsWith("unmark")) {
                int taskIndex = parseTaskIndex(input, 7);
                tasks[taskIndex].markAsNotDone();
                printMarkResult(tasks[taskIndex], false);
            } else {
                printInput(input);
                tasks[taskCount] = new Task(input);
                taskCount++;
            }
        }
        scanner.close();
    }

    private static int parseTaskIndex(String input, int commandLength) {
        return Integer.parseInt(input.substring(commandLength)) - 1;
    }

    public static void printInput(String input) {
        System.out.print("Let's add that to the words of wisdom: ");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }

    public static void printList(Task[] tasks, int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            int number = i + 1;
            Task task = tasks[i];

            String status = "[ ] ";
            if (task.isDone()) {
                status = "[X] ";
            }

            System.out.println("     " + (i+1) + ". " + status + task.getDescription());
        }
        System.out.println("____________________________________________________________");
    }

    private static void printMarkResult(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Congratulations on the task, it was such a success:");
        } else {
            System.out.println("Retired the task:");
        }

        String status = "[ ] ";
        if (task.isDone()) {
            status = "[X] ";
        }

        System.out.println("  " + status + task.getDescription());
        System.out.println("____________________________________________________________");
    }
}
