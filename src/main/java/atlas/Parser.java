package atlas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static ParsedCommand parse(String input) throws AtlasException {
        assert input != null : "Parser received a null input string";
        input = input.trim();

        if (input.equals("bye")) return new ParsedCommand(CommandType.BYE);
        if (input.equals("list")) return new ParsedCommand(CommandType.LIST);

        if (input.startsWith("todo")) return parseTodo(input);
        if (input.startsWith("deadline")) return parseDeadline(input);
        if (input.startsWith("event")) return parseEvent(input);

        // Handle mark/unmark/delete
        if (input.startsWith("mark")) return new ParsedCommand(CommandType.MARK, parseIndex(input, 5));
        if (input.startsWith("unmark")) return new ParsedCommand(CommandType.UNMARK, parseIndex(input, 7));
        if (input.startsWith("delete")) return new ParsedCommand(CommandType.DELETE, parseIndex(input, 7));

        throw new AtlasException("I don't know what that command means.");
    }

    private static ParsedCommand parseTodo(String input) throws AtlasException {
        if (input.length() <= 5) throw new AtlasException("A todo needs a description.");
        return new ParsedCommand(CommandType.TODO, input.substring(5));
    }

    private static ParsedCommand parseDeadline(String input) throws AtlasException {
        if (!input.contains("/by")) throw new AtlasException("A deadline needs a /by date.");
        String[] parts = input.substring(9).split("/by", 2);
        try {
            LocalDate by = LocalDate.parse(parts[1].trim());
            return new ParsedCommand(CommandType.DEADLINE, parts[0].trim(), by);
        } catch (DateTimeParseException e) {
            throw new AtlasException("Please use date format yyyy-mm-dd.");
        }
    }
    
    private static int parseIndex(String input, int start) throws AtlasException {
        try {
            return Integer.parseInt(input.substring(start).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AtlasException("Please provide a valid task number.");
        }
    }
}

