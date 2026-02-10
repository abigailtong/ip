
/**
 * Handles parsing and validation of user input.
 * Converts raw input into command arguments usable by Miffy.
 */
public class MiffyReader {

    /**
     * Parses and validates user input.
     *
     * @param input Raw user input.
     * @param taskCount Current number of tasks.
     * @return String array of command and arguments.
     * @throws MiffyException if command is invalid.
     */
    public static String[] readInput(String input, int taskCount) throws MiffyException {

        if (input == null || input.trim().isEmpty()) {
            throw new MiffyException("I am miffed that you did not say anything!");
        }

        String[] commandArguments = input.trim().toLowerCase().split(" ", 2);
        String command = commandArguments[0];

        int length = commandArguments.length;
        boolean isInvalid;

        switch (command) {

            case "bye":
                if (length != 1) {
                    throw new MiffyException("Miffy thinks you should type: bye");
                }
                return commandArguments;

            case "list":
                if (length != 1) {
                    throw new MiffyException("Miffy thinks you should type: list");
                }

                if (taskCount == 0) {
                    throw new MiffyException("I have no tasks to show you!");
                }
                return commandArguments;

            case "mark":

                if (length < 2 || commandArguments[1].trim().isEmpty()) {
                    throw new MiffyException("You did not tell me the task number!");
                }

                int markIndex;
                try {
                    markIndex = Integer.parseInt(commandArguments[1].trim()) - 1;
                } catch (NumberFormatException e) {
                    throw new MiffyException("That is not a valid task number!");
                }

                // Range validation: 0 < index < taskCount - 1
                if ((markIndex < 0) || (markIndex > taskCount - 1)) {
                    throw new MiffyException("Task number is out of valid range!");
                }

                return commandArguments;

            case "unmark":
                if (length < 2 || commandArguments[1].trim().isEmpty()) {
                    throw new MiffyException("You did not tell me the task number!");
                }

                int unmarkIndex;
                try {
                    unmarkIndex = Integer.parseInt(commandArguments[1].trim()) - 1;
                } catch (NumberFormatException e) {
                    throw new MiffyException("That is not a valid task number!");
                }

                // Range validation: 0 < index < taskCount - 1
                if ((unmarkIndex < 0) || (unmarkIndex > taskCount - 1)) {
                    throw new MiffyException("Task number is out of valid range!");
                }

                return commandArguments;

            case "todo":

                isInvalid = (length < 2) || commandArguments[1].trim().isEmpty();

                if (isInvalid) {
                    throw new MiffyException("Miffy thinks you should type: todo <description>");
                }

                return commandArguments;

            case "deadline":

                isInvalid = (length < 2)
                        || !commandArguments[1].contains(" /by ");

                if (isInvalid) {
                    throw new MiffyException(
                            "Miffy thinks you should type: deadline <description> /by <time>");
                }

                return commandArguments;

            case "event":

                isInvalid = (length < 2)
                        || !commandArguments[1].contains(" /from ")
                        || !commandArguments[1].contains(" /to ");

                if (isInvalid) {
                    throw new MiffyException(
                            "Miffy thinks you should type: event <description> /from <time> /to <time>");
                }

                return commandArguments;

            default:
                throw new MiffyException("Miffy is too stunned to speak.");
        }
    }
}
