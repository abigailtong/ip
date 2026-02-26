package miffy.task;

/**
 * Represents a ToDo task without a specific deadline or time.
 * Extends the Task class by specifying the task type as ToDo.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo task,
     * including its type, status, and description.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string suitable for saving the ToDo task to file,
     * including its type, completion status, and description.
     *
     * @return A string representing the ToDo task for storage.
     */
    @Override
    public String toSavingString() {
        return "T" + " | " + super.toSavingString();
    }
}