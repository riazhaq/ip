package atlas;

import java.time.LocalDate;

public class ParsedCommand {

    private final CommandType commandType;
    private final String argument;
    private final int index;

    private final LocalDate date;
    private final LocalDate from;
    private final LocalDate to;

    // For simple commands (list, exit)
    public ParsedCommand(CommandType commandType) {
        this.commandType = commandType;
        this.argument = null;
        this.index = -1;
        this.date = null;
        this.from = null;
        this.to = null;
    }

    // For commands with description (todo, find)
    public ParsedCommand(CommandType commandType, String argument) {
        this.commandType = commandType;
        this.argument = argument;
        this.index = -1;
        this.date = null;
        this.from = null;
        this.to = null;
    }

    // For commands with index (mark, delete)
    public ParsedCommand(CommandType commandType, int index) {
        this.commandType = commandType;
        this.index = index;
        this.argument = null;
        this.date = null;
        this.from = null;
        this.to = null;
    }

    // For deadline
    public ParsedCommand(CommandType commandType, String argument, LocalDate date) {
        this.commandType = commandType;
        this.argument = argument;
        this.date = date;
        this.index = -1;
        this.from = null;
        this.to = null;
    }

    // For event
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