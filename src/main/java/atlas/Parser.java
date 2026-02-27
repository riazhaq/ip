package atlas;

public class Parser {

    public static ParsedCommand parse(String input) throws AtlasException {
        String trimmed = input.trim();

        if (trimmed.equals("list")) {
            return new ParsedCommand(CommandType.LIST);
        }

        if (trimmed.equals("exit") || trimmed.equals("bye")) {
            return new ParsedCommand(CommandType.EXIT);
        }

        // Added with AI assistance to support the sorting feature
        if (trimmed.equals("sort")) {
            return new ParsedCommand(CommandType.SORT);
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
            if (!details.contains(" /by ")) {
                throw new AtlasException("Please use: deadline [desc] /by [yyyy-mm-dd]");
            }
            String[] parts = details.split(" /by ");
            // description = parts[0], date = LocalDate.parse(parts[1])
            return new ParsedCommand(CommandType.DEADLINE, parts[0], java.time.LocalDate.parse(parts[1]));
        }

        if (trimmed.startsWith("event")) {
            String details = trimmed.substring(5).trim();
            if (!details.contains(" /from ") || !details.contains(" /to ")) {
                throw new AtlasException("Please use: event [desc] /from [yyyy-mm-dd] /to [yyyy-mm-dd]");
            }
            String[] parts = details.split(" /from | /to ");
            // description = parts[0], from = parts[1], to = parts[2]
            return new ParsedCommand(CommandType.EVENT, parts[0],
                    java.time.LocalDate.parse(parts[1]), java.time.LocalDate.parse(parts[2]));
        }

        throw new AtlasException("I'm sorry, but I don't know what that means.");
    }

    /**
     * Helper method to parse the task index from a command string.
     */
    private static int parseIndex(String input, int start) throws AtlasException {
        try {
            String indexPart = input.substring(start).trim();
            if (indexPart.isEmpty()) {
                throw new AtlasException("Please provide a task number.");
            }
            return Integer.parseInt(indexPart);
        } catch (NumberFormatException e) {
            throw new AtlasException("Please provide a valid task number.");
        }
    }
}