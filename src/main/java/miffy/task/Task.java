package miffy.task;

/**
 * Represents a task with a description and completion status.
 *
 * This class provides methods to get and set the completion status,
 * and to return the task as a string for display or for saving to storage.
 */
public class Task {

    /** Description of the task. */
    private final String description;

    /** Completion status of the task. True if done, false otherwise. */
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task for display.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the string representation of the task for display.
     *
     * Includes the status icon and the description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    /**
     * Returns a string representation of the task for saving to storage.
     *
     * Format is: "isDone | description"
     *
     * @return A string suitable for saving to persistent storage.
     */
    public String toSavingString() {
        return isDone + " | " + description;
    }
}