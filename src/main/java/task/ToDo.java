package task;

/**
 * Represents a task.ToDo without a specific deadline or time.
 * Extends the task.Task class by providing a task.ToDo type.
 */
public class ToDo extends Task {

    /**
     * Constructs a task.ToDo task with the specified description.
     *
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task,
     * including the type and description.
     *
     * @return A string of the type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
