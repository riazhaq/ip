package atlas;

import java.time.LocalDate;

/**
 * Represents a command that has been parsed from user input.
 * It encapsulates the type of command and any associated data, such as
 * descriptions, task indices, or dates.
 */
public class ParsedCommand {

    private final CommandType commandType;
    private final String argument;
    private final int index;

    private final LocalDate date;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs a ParsedCommand for simple actions like listing or exiting.
     * @param commandType The type of command to execute.
     */
    public ParsedCommand(CommandType commandType) {
        this.commandType = commandType;
        this.argument = null;
        this.index = -1;
        this.date = null;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs a ParsedCommand that requires a description or keyword.
     * @param commandType The type of command (e.g., TODO, FIND).
     * @param argument The description or keyword provided by the user.
     */
    public ParsedCommand(CommandType commandType, String argument) {
        this.commandType = commandType;
        this.argument = argument;
        this.index = -1;
        this.date = null;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs a ParsedCommand that targets a specific task by its index.
     * @param commandType The type of command (e.g., MARK, DELETE).
     * @param index The 1-based index of the target task.
     */
    public ParsedCommand(CommandType commandType, int index) {
        this.commandType = commandType;
        this.index = index;
        this.argument = null;
        this.date = null;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs a ParsedCommand for deadlines with a description and a due date.
     * @param commandType Should be CommandType.DEADLINE.
     * @param argument The task description.
     * @param date The deadline date.
     */
    public ParsedCommand(CommandType commandType, String argument, LocalDate date) {
        this.commandType = commandType;
        this.argument = argument;
        this.date = date;
        this.index = -1;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructs a ParsedCommand for events spanning a date range.
     * @param commandType Should be CommandType.EVENT.
     * @param argument The event description.
     * @param from The start date.
     * @param to The end date.
     */
    public ParsedCommand(CommandType commandType, String argument,
                         LocalDate from, LocalDate to) {
        this.commandType = commandType;
        this.argument = argument;
        this.from = from;
        this.to = to;
        this.index = -1;
        this.date = null;
    }


    public CommandType getCommandType() {
        return commandType;
    }

    public String getArgument() {
        return argument;
    }

    public int getIndex() {
        return index;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}