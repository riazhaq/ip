package atlas;

public class Parser {

    public static ParsedCommand parse(String input) throws AtlasException {
        String trimmed = input.trim();

        if (trimmed.equals("list")) {
            return new ParsedCommand(CommandType.LIST);
        }

        if (trimmed.equals("exit")) {
            return new ParsedCommand(CommandType.EXIT);
        }

        if (trimmed.startsWith("find")) {
            String keyword = trimmed.substring(4).trim();
            if (keyword.isEmpty()) {
                throw new AtlasException("Please provide a keyword to search for.");
            }
            return new ParsedCommand(CommandType.FIND, keyword);
        }

        if (trimmed.startsWith("mark")) {
            int index = parseIndex(trimmed, 4);
            return new ParsedCommand(CommandType.MARK, index);
        }

        if (trimmed.startsWith("unmark")) {
            int index = parseIndex(trimmed, 6);
            return new ParsedCommand(CommandType.UNMARK, index);
        }

        if (trimmed.startsWith("delete")) {
            int index = parseIndex(trimmed, 6);
            return new ParsedCommand(CommandType.DELETE, index);
        }

        if (trimmed.startsWith("todo")) {
            String description = trimmed.substring(4).trim();
            if (description.isEmpty()) {
                throw new AtlasException("The description of a todo cannot be empty.");
            }
            return new ParsedCommand(CommandType.TODO, description);
        }

        if (trimmed.startsWith("deadline")) {
            String details = trimmed.substring(8).trim();
            return new ParsedCommand(CommandType.DEADLINE, details);
        }

        if (trimmed.startsWith("event")) {
            String details = trimmed.substring(5).trim();
            return new ParsedCommand(CommandType.EVENT, details);
        }

        throw new AtlasException("I'm sorry, but I don't know what that means.");
    }

    private static int parseIndex(String input, int start) throws AtlasException {
        try {
            return Integer.parseInt(input.substring(start).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AtlasException("Please provide a valid task number.");
        }
    }
}
