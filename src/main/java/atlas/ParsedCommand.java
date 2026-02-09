package atlas;

import java.time.LocalDate;

/**
 * Represents a parsed user command with its type and arguments.
 */
public class ParsedCommand {
    private final CommandType commandType;
    private final String argument;
    private final int index;

    // Constructor for commands with argument (e.g. find, todo, deadline)
    public ParsedCommand(CommandType commandType, String argument) {
        this.commandType = commandType;
        this.argument = argument;
        this.index = -1;
    }

    // Constructor for commands with index (e.g. mark, delete)
    public ParsedCommand(CommandType commandType, int index) {
        this.commandType = commandType;
        this.index = index;
        this.argument = null;
    }

    // Constructor for commands without arguments (e.g. list, exit)
    public ParsedCommand(CommandType commandType) {
        this.commandType = commandType;
        this.argument = null;
        this.index = -1;
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
}
