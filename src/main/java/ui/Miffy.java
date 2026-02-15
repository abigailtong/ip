package ui;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.Deadline;
import task.ToDo;
import task.Event;
import reader.MiffyReader;

/**
 * Main class for the ui.Miffy task management program.
 * Handles user input and manages a list of ToDos, task.Event & task.Deadline.
 */
public class Miffy {

    /**
     * The main entry point of the program.
     * Prints the logo and starts handling user input.
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

    public static void handleUserInput(Scanner scanner) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        int taskIndex;
        Task task;
        String taskDescription;
        String[] parts;

        while (true) {

            String input = scanner.nextLine();

            String[] commandArguments;
            try {
                commandArguments = MiffyReader.readInput(input, tasks);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            String command = commandArguments[0];

            switch (command) {
            case "list":
                System.out.println("We are checking:");
                printList(tasks);
                break;

            case "bye":
                System.out.println("As always sir, a great pleasure watching you work!");
                return;

            case "mark":
                taskIndex = Integer.parseInt(commandArguments[1].trim()) - 1;
                task = tasks.get(taskIndex);
                task.markAsDone();
                printMarkResult(task, true);
                break;

            case "unmark":
                taskIndex = Integer.parseInt(commandArguments[1].trim()) - 1;
                task = tasks.get(taskIndex);
                task.markAsDone();
                printMarkResult(task, false);
                break;

            case "todo":
                taskDescription = commandArguments[1];
                tasks.add(new ToDo(taskDescription));
                printInput(tasks);
                break;

            case "deadline":
                parts = commandArguments[1].split(" /by ");
                taskDescription = parts[0].trim();
                String by = parts[1].trim();
                tasks.add(new Deadline(taskDescription, by));
                printInput(tasks);
                break;

            case "event":
                parts = commandArguments[1].split(" /from | /to ");
                taskDescription = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                tasks.add(new Event(taskDescription, from, to));
                printInput(tasks);
                break;

            case "delete":
                taskIndex = Integer.parseInt(commandArguments[1].trim()) - 1;
                deleteTask(tasks, taskIndex);
                break;

            }
        }
    }


    /**
     * Prints the added task and the updated task count.
     *
     * @param tasks      The list of tasks.
     */
    public static void printInput(ArrayList<Task> tasks) {
        int topIndex = tasks.size() - 1;
        System.out.println("Let's add that to the words of wisdom:");
        System.out.println(" " + tasks.get(topIndex));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the list of all tasks.
     *
     * @param tasks     Array of tasks.
     */
    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the result of marking or unmarking a task.
     *
     * @param task   The task being updated.
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


    /**
     * Deletes the task with the given index.
     *
     * @param tasks      The list of tasks.
     * @param index      The index of the task to delete.
     */
    public static void deleteTask(ArrayList<Task> tasks, int index) {
        System.out.println("Let's remove this from the words of wisdom:");
        System.out.println(" " + tasks.get(index));
        tasks.remove(index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

}
