package task;

import exception.MiffyException;
import ui.Miffy;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and handles operations such as adding, deleting, updating, and searching tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /** Initializes an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Initializes a task list with an existing list of tasks. */
    public TaskList(List<Task> initialTasks) {
        this.tasks = new ArrayList<>(initialTasks);
    }

    /** Adds a generic task to the list. */
    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    /** Returns the task at the specified index. */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /** Returns the current number of tasks in the list. */
    public int size() {
        return tasks.size();
    }

    /** Updates the completion status of a task at the specified index. */
    public void updateTaskStatus(int index, boolean isDone) {
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
            System.out.println("Congratulations, you've completed the task!");
        } else {
            task.markAsNotDone();
            System.out.println("Task marked as not done.");
        }
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    /** Prints all tasks with their indices. */
    public void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks at the moment!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /** Adds a new ToDo task and prints a confirmation. */
    public void addToDo(String description) {
        Task todo = new ToDo(description);
        addTaskToList(todo);
        System.out.println("Added new ToDo: " + todo);
        System.out.println("Now you have " + size() + " tasks.");
        System.out.println("____________________________________________________________");
    }

    /** Adds a new Deadline task and prints a confirmation. */
    public void addDeadline(String description, String by) throws MiffyException {
        if (description.isBlank() || by.isBlank()) {
            throw new MiffyException("Deadline description and time cannot be empty!");
        }
        Task deadline = new Deadline(description, by);
        addTaskToList(deadline);
        System.out.println("Added new Deadline: " + deadline);
        System.out.println("Now you have " + size() + " tasks.");
        System.out.println("____________________________________________________________");
    }

    /** Adds a new Event task and prints a confirmation. */
    public void addEvent(String description, String from, String to) throws MiffyException {
        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new MiffyException("Event description, from and to times cannot be empty!");
        }
        Task event = new Event(description, from, to);
        addTaskToList(event);
        System.out.println("Added new Event: " + event);
        System.out.println("Now you have " + size() + " tasks.");
        System.out.println("____________________________________________________________");
    }

    /** Deletes a task at the specified index and prints a confirmation. */
    public void deleteTask(int index) {
        Task removed = tasks.remove(index);
        System.out.println("Removed task: " + removed);
        System.out.println("Now you have " + size() + " tasks.");
        System.out.println("____________________________________________________________");
    }

    /** Returns a list of tasks containing the given keyword (case-insensitive). */
    public TaskList findTasks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        TaskList found = new TaskList();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(lowerKeyword)) {
                found.addTaskToList(t);
            }
        }
        return found;
    }

    /** Returns a copy of all tasks. */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}