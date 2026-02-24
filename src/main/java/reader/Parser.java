package reader;

import exception.MiffyException;
import task.TaskList;

/**
 * Handles parsing and validation of user input.
 */
public class Parser {

    /**
     * Parses and validates user input.
     *
     * @param input Raw user input.
     * @param taskList The current list of tasks for range validation.
     * @return String array of command and arguments.
     * @throws MiffyException if command is invalid.
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

    /** Validates that the command has the exact expected length. */
    private static void validateLength(String command, int length, int expected) throws MiffyException {
        if (length != expected) {
            throw new MiffyException("Miffy thinks you should type: " + command);
        }
    }

    /**
     * Parses a 1-based index from commandArguments and validates its range.
     *
     * @param commandArguments The raw command arguments.
     * @param taskCount Current number of tasks.
     * @param command Command name (mark/unmark/delete).
     * @return zero-based index.
     * @throws MiffyException if invalid number or out of range.
     */
    private static int parseIndex(String[] commandArguments, int taskCount, String command) throws MiffyException {
        if (commandArguments.length < 2 || commandArguments[1].trim().isEmpty()) {
            throw new MiffyException("You did not tell me the task number!");
        }

        int index;
        try {
            index = Integer.parseInt(commandArguments[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MiffyException("That is not a valid task number!");
        }

        if (index < 0 || index >= taskCount) {
            throw new MiffyException("Task number is out of valid range!");
        }

        return index;
    }
}