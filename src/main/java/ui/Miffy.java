package ui;

import saving.Storage;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import task.TaskList;
import reader.Parser;

import java.util.List;
import java.util.Scanner;

/**
 * Main Miffy application that handles user input and task management.
 */
public class Miffy {

    private final Ui ui;
    private final Scanner scanner;
    private final Storage storage;
    private final TaskList tasks;

    public Miffy() {
        this.ui = new Ui();
        this.scanner = new Scanner(System.in);
        this.storage = new Storage();
        this.tasks = storage.loadFromFile();

        ui.showWelcome();
    }

    public void run() {
        boolean isExit = false;

        while (!isExit) {
            try {
                ui.showPrompt();
                String input = scanner.nextLine();

                String[] commandArguments = Parser.readInput(input, tasks);
                String command = commandArguments[0];

                switch (command) {
                    case "list":
                        ui.showTaskList(tasks); // Pass TaskList directly
                        break;

                    case "bye":
                        storage.saveToFile(tasks);
                        ui.showExit();
                        isExit = true;
                        break;

                    case "mark":
                        int markIndex = Integer.parseInt(commandArguments[1].trim()) - 1;
                        Task markTask = tasks.getTask(markIndex);
                        markTask.markAsDone();
                        ui.showTaskMarked(markTask);
                        storage.saveToFile(tasks);
                        break;

                    case "unmark":
                        int unmarkIndex = Integer.parseInt(commandArguments[1].trim()) - 1;
                        Task unmarkTask = tasks.getTask(unmarkIndex);
                        unmarkTask.markAsNotDone();
                        ui.showTaskUnmarked(unmarkTask);
                        storage.saveToFile(tasks);
                        break;

                    case "todo":
                        String todoDesc = commandArguments[1];
                        Task todoTask = new ToDo(todoDesc);
                        tasks.addTaskToList(todoTask);
                        ui.showTaskAdded(todoTask, tasks);
                        storage.saveToFile(tasks);
                        break;

                    case "deadline":
                        String[] deadlineParts = commandArguments[1].split(" /by ");
                        String deadlineDesc = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();
                        Task deadlineTask = new Deadline(deadlineDesc, by);
                        tasks.addTaskToList(deadlineTask);
                        ui.showTaskAdded(deadlineTask, tasks);
                        storage.saveToFile(tasks);
                        break;

                    case "event":
                        String[] eventParts = commandArguments[1].split(" /from | /to ");
                        String eventDesc = eventParts[0].trim();
                        String from = eventParts[1].trim();
                        String to = eventParts[2].trim();
                        Task eventTask = new Event(eventDesc, from, to);
                        tasks.addTaskToList(eventTask);
                        ui.showTaskAdded(eventTask, tasks);
                        storage.saveToFile(tasks);
                        break;

                    case "delete":
                        int delIndex = Integer.parseInt(commandArguments[1].trim()) - 1;
                        Task delTask = tasks.getTask(delIndex);
                        tasks.deleteTask(delIndex);
                        ui.showDeletedTask(delTask, tasks.size());
                        storage.saveToFile(tasks);
                        break;

                    case "find":
                        String keyword = commandArguments[1].trim();
                        TaskList foundTasks = tasks.findTasks(keyword);

                        if (foundTasks.size() == 0) {
                            ui.showError("Miffy could not find any matching tasks!");
                        } else {
                            ui.showFindResults(foundTasks, keyword);
                        }
                        break;

                    default:
                        ui.showError("Unknown command. Try again!");
                }

            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Miffy().run();
    }
}