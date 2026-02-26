package miffy.reader;

import miffy.exception.MiffyException;
import miffy.task.TaskList;

/**
 * Handles parsing and validation of user input for the Miffy application.
 * Converts raw user input into validated commands and arguments.
 */
public class Parser {

    /**
     * Parses and validates raw user input.
     * Converts it into a command and its arguments for execution.
     *
     * @param input Raw user input string.
     * @param taskList The current list of tasks for range validation.
     * @return Array of strings containing the command and its arguments.
     * @throws MiffyException if the input is invalid or violates command format rules.
     */
    public static String[] readInput(String input, TaskList taskList) throws MiffyException {

        if (input == null || input.trim().isEmpty()) {
            throw new MiffyException("I am miffed that you did not say anything!");
        }

        String[] commandArguments = input.trim().toLowerCase().split(" ", 2);
        String command = commandArguments[0];
        int length = commandArguments.length;

        switch (command) {

            case "bye":
                validateLength(command, length, 1);
                return commandArguments;

            case "list":
                validateLength(command, length, 1);
                if (taskList.size() == 0) {
                    throw new MiffyException("I have no tasks to show you!");
                }
                return commandArguments;

            case "mark":
                int markIndex = parseIndex(commandArguments, taskList.size(), command);
                return new String[]{command, String.valueOf(markIndex + 1)}; // 1-based index

            case "unmark":
                int unmarkIndex = parseIndex(commandArguments, taskList.size(), command);
                return new String[]{command, String.valueOf(unmarkIndex + 1)}; // 1-based index

            case "delete":
                int deleteIndex = parseIndex(commandArguments, taskList.size(), command);
                return new String[]{command, String.valueOf(deleteIndex + 1)}; // 1-based index

            case "todo":
                if (length < 2 || commandArguments[1].trim().isEmpty()) {
                    throw new MiffyException("Miffy thinks you should type: todo <description>");
                }
                return commandArguments;

            case "deadline":
                if (length < 2 || !commandArguments[1].contains(" /by ")) {
                    throw new MiffyException(
                            "Miffy thinks you should type: deadline <description> /by <time>");
                }
                return commandArguments;

            case "event":
                if (length < 2 || !commandArguments[1].contains(" /from ") || !commandArguments[1].contains(" /to ")) {
                    throw new MiffyException(
                            "Miffy thinks you should type: event <description> /from <time> /to <time>");
                }
                return commandArguments;

            case "find":
                if (length < 2 || commandArguments[1].trim().isEmpty()) {
                    throw new MiffyException("Miffy thinks you should type: find <keyword>");
                }
                return commandArguments;

            default:
                throw new MiffyException("Miffy is too stunned to speak.");
        }
    }

    /**
     * Validates that a command has the exact expected number of arguments.
     *
     * @param command The command being validated.
     * @param length The actual number of arguments.
     * @param expected The expected number of arguments.
     * @throws MiffyException if the command does not match the expected length.
     */
    private static void validateLength(String command, int length, int expected) throws MiffyException {
        if (length != expected) {
            throw new MiffyException("Miffy thinks you should type: " + command);
        }
    }

    /**
     * Parses a 1-based task index from the command arguments and validates it.
     *
     * @param commandArguments Array containing command and arguments.
     * @param taskCount Total number of tasks currently in the list.
     * @param command The command name (e.g., mark, unmark, delete).
     * @return Zero-based index corresponding to the task.
     * @throws MiffyException if the index is missing, not a number, or out of range.
     */
    private static int parseIndex(String[] commandArguments, int taskCount, String command) throws MiffyException {
        if (commandArguments.length < 2 || commandArguments[1].trim().isEmpty()) {
            throw new MiffyException("You did not tell me the miffy.task number!");
        }

        int index;
        try {
            index = Integer.parseInt(commandArguments[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MiffyException("That is not a valid miffy.task number!");
        }

        if (index < 0 || index >= taskCount) {
            throw new MiffyException("Task number is out of valid range!");
        }

        return index;
    }
}