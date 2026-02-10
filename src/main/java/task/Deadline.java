package task;

/**
 * Represents a task with a deadline.
 * Extends the task.Task class by adding a due date/time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a deadline with the specified description and deadline.
     *
     * @param description Description of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline.
     * including the type, description, and deadline.
     *
     * @return A string of the status and description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
