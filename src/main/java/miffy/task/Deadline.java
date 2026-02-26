package miffy.task;

/**
 * Represents a task with a deadline.
 *
 * This class extends Task by adding a due date or time for the task.
 * It provides methods to return the task as a string for display
 * and for saving to persistent storage.
 */
public class Deadline extends Task {

    /** The due date or time of the deadline task. */
    protected String by;

    /**
     * Constructs a Deadline task with a description and a due date/time.
     *
     * @param description Description of the task.
     * @param by The due date or time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task for display.
     *
     * Includes the task type ([D]), completion status, description,
     * and due date/time.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline task for saving.
     *
     * Format is: "D | isDone | description | by"
     *
     * @return A string suitable for saving to storage.
     */
    public String toSavingString() {
        return "D" + " | " + super.toSavingString() + " | " + by;
    }
}