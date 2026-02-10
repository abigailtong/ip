package task;

/**
 * Represents an task.Event that occurs within a specific time period.
 * Extends the task.Task class by adding a start and end time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an event with the specified description, start time, and end time.
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
     * Returns the string representation of the event.
     * including the type, description, and deadline.
     *
     * @return A string of the status and description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }
}
