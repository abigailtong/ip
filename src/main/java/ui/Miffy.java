package ui;

import exception.MiffyException;
import reader.Parser;
import saving.Storage;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

import java.util.Scanner;

/**
 * Main class for the Miffy task management program.
 * Handles user input and manages a list of tasks.
 */
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
        Storage storage = new Storage();
        TaskList taskList = storage.loadFromFile();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("I am miffed that you did not say anything!");
                continue;
            }

            String[] commandArguments;
            try {
                commandArguments = Parser.readInput(input, taskList);
            } catch (MiffyException e) {
                System.out.println(e.getMessage());
                continue;
            }

            String command = commandArguments[0];

            try {
                switch (command) {
                    case "list" -> taskList.printAllTasks();

                    case "bye" -> {
                        System.out.println("As always sir, a great pleasure watching you work!");
                        storage.saveToFile(taskList);
                        return;
                    }

                    case "mark" -> {
                        int index = Integer.parseInt(commandArguments[1].trim()) - 1;
                        taskList.updateTaskStatus(index, true);
                        storage.saveToFile(taskList);
                    }

                    case "unmark" -> {
                        int index = Integer.parseInt(commandArguments[1].trim()) - 1;
                        taskList.updateTaskStatus(index, false);
                        storage.saveToFile(taskList);
                    }

                    case "todo" -> {
                        String description = commandArguments[1].trim();
                        taskList.addToDo(description);
                        storage.saveToFile(taskList);
                    }

                    case "deadline" -> {
                        String[] parts = commandArguments[1].split(" /by ", 2);
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        taskList.addDeadline(description, by);
                        storage.saveToFile(taskList);
                    }

                    case "event" -> {
                        String[] partsFrom = commandArguments[1].split(" /from ", 2);
                        String description = partsFrom[0].trim();
                        String[] partsTo = partsFrom[1].split(" /to ", 2);
                        String from = partsTo[0].trim();
                        String to = partsTo[1].trim();
                        taskList.addEvent(description, from, to);
                        storage.saveToFile(taskList);
                    }

                    case "delete" -> {
                        int index = Integer.parseInt(commandArguments[1].trim()) - 1;
                        taskList.deleteTask(index);
                        storage.saveToFile(taskList);
                    }

                    default -> System.out.println("Miffy is too stunned to speak.");
                }
            } catch (Exception e) {
                System.out.println("Error handling command: " + e.getMessage());
            }
        }
    }
}