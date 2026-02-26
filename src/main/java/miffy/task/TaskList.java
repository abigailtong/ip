package miffy.task;

import miffy.exception.MiffyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manage them.
 *
 * Supports adding, deleting, updating status, printing, and searching tasks.
 */
public class TaskList {

    /** Internal list storing all tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task to retrieve.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Updates the completion status of the task at the given index.
     *
     * Prints the updated task to the console.
     *
     * @param index Index of the task to update.
     * @param isDone True to mark as done, false to mark as not done.
     */
    public void updateTaskStatus(int index, boolean isDone) {
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
            System.out.println("Congratulations, you've completed the miffy.task!");
        } else {
            task.markAsNotDone();
            System.out.println("Task marked as not done.");
        }
        System.out.println(" " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints all tasks in the list with their indices.
     * Prints a message if the list is empty.
     */
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

    /**
     * Adds a new ToDo task and prints a confirmation.
     *
     * @param description Description of the ToDo task.
     */
    public void addToDo(String description) {
        Task todo = new ToDo(description);
        addTaskToList(todo);
        System.out.println("Added new ToDo: " + todo);
        System.out.println("Now you have " + size() + " tasks.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Adds a new Deadline task and prints a confirmation.
     *
     * @param description Description of the deadline task.
     * @param by Deadline time.
     * @throws MiffyException If description or deadline time is blank.
     */
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

    /**
     * Adds a new Event task and prints a confirmation.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     * @throws MiffyException If description, from, or to is blank.
     */
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

    /**
     * Deletes the task at the specified index and prints a confirmation.
     *
     * @param index Index of the task to delete.
     */
    public void deleteTask(int index) {
        Task removed = tasks.remove(index);
        System.out.println("Removed miffy.task: " + removed);
        System.out.println("Now you have " + size() + " tasks.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Finds tasks containing the given keyword, set to lowercase.
     *
     * @param keyword Keyword to search for.
     * @return A new TaskList containing matching tasks.
     */
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

    /**
     * Returns a copy of all tasks in the list.
     *
     * @return A list of all tasks.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}