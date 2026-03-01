package atlas;

/**
 * Represents errors specific to the Atlas application.
 * This exception is thrown when the user provides invalid commands,
 * incorrect data formats, or triggers logic errors like duplicate tasks.
 */
public class AtlasException extends Exception {
    /**
     * Constructs a new AtlasException with the specified detail message.
     * * @param message The error message explaining the cause of the exception.
     */
    public AtlasException(String message) {
        super(message);
    }
}


