package atlas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static ParsedCommand parse(String input) throws AtlasException {
        // Assertion: The UI should never pass a null string to the parser
        assert input != null : "Parser received a null input string";

        input = input.trim();

        if (input.equals("bye")) {
            return new ParsedCommand(CommandType.BYE);
        }

        if (input.equals("list")) {
            return new ParsedCommand(CommandType.LIST);
        }

        if (input.startsWith("todo")) {
            if (input.length() <= 4) {
                throw new AtlasException("A todo needs a description.");
            }
            String desc = input.substring(5);
            // Assertion: description should not be null after substring logic
            assert desc != null : "Todo description is null";
            return new ParsedCommand(CommandType.TODO, desc);
        }

        if (input.startsWith("deadline")) {
            if (!input.contains("/by")) {
                throw new AtlasException("A deadline needs a /by date.");
            }

            String[] parts = input.substring(9).split("/by", 2);
            if (parts[0].trim().isEmpty()) {
                throw new AtlasException("Deadline description cannot be empty.");
            }

            try {
                LocalDate by = LocalDate.parse(parts[1].trim());
                return new ParsedCommand(CommandType.DEADLINE, parts[0].trim(), by);
            } catch (DateTimeParseException e) {
                throw new AtlasException("Please use date format yyyy-mm-dd.");
            }
        }

        if (input.startsWith("event")) {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new AtlasException("An event needs /from and /to dates.");
            }

            String[] parts = input.substring(6).split("/from|/to");
            // Assertion: split should result in at least 3 parts for an event
            assert parts.length >= 3 : "Event command split incorrectly";

            if (parts[0].trim().isEmpty()) {
                throw new AtlasException("Event description cannot be empty.");
            }

            try {
                LocalDate from = LocalDate.parse(parts[1].trim());
                LocalDate to = LocalDate.parse(parts[2].trim());
                return new ParsedCommand(CommandType.EVENT, parts[0].trim(), from, to);
            } catch (DateTimeParseException e) {
                throw new AtlasException("Please use date format yyyy-mm-dd.");
            }
        }

        if (input.startsWith("mark")) {
            return new ParsedCommand(CommandType.MARK, parseIndex(input, 5));
        }

        if (input.startsWith("unmark")) {
            return new ParsedCommand(CommandType.UNMARK, parseIndex(input, 7));
        }

        if (input.startsWith("delete")) {
            return new ParsedCommand(CommandType.DELETE, parseIndex(input, 7));
        }

        throw new AtlasException("I don't know what that command means.");
    }

    private static int parseIndex(String input, int start) throws AtlasException {
        try {
            return Integer.parseInt(input.substring(start).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AtlasException("Please provide a valid task number.");
        }
    }
}