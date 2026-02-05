package atlas;

import java.time.LocalDate;

public class ParsedCommand {
    public final CommandType type;
    public final String description;
    public final LocalDate date1;
    public final LocalDate date2;
    public final int index;

    // For commands like bye, list
    public ParsedCommand(CommandType type) {
        this(type, null, null, null, -1);
    }

    // For todo
    public ParsedCommand(CommandType type, String description) {
        this(type, description, null, null, -1);
    }

    // For deadline
    public ParsedCommand(CommandType type, String description, LocalDate date) {
        this(type, description, date, null, -1);
    }

    // For event
    public ParsedCommand(CommandType type, String description, LocalDate from, LocalDate to) {
        this(type, description, from, to, -1);
    }

    // For mark / unmark / delete
    public ParsedCommand(CommandType type, int index) {
        this(type, null, null, null, index);
    }

    private ParsedCommand(CommandType type, String desc, LocalDate d1, LocalDate d2, int index) {
        this.type = type;
        this.description = desc;
        this.date1 = d1;
        this.date2 = d2;
        this.index = index;
    }
}

