package miffy.task;

/**
 * Represents a task that occurs within a specific time period.
 *
 * This class extends Task by adding a start time and an end time for the event.
 * It provides methods to return the event as a string for display and for saving
 * to persistent storage.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected String from;

    /** The end time of the event. */
    protected String to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task for display.
     *
     * Includes the task type ([E]), completion status, description,
     * and start/end times.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the event task for saving.
     *
     * Format is: "E | isDone | description | from | to"
     *
     * @return A string suitable for saving to storage.
     */
    @Override
    public String toSavingString() {
        return "E" + " | " + super.toSavingString() + " | " + from + " | " + to;
    }
}