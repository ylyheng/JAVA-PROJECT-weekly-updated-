package src.Exception;
/**
 * Custom Exception for invalid book IDs
 */
public class InvalidBookIdException extends Exception {
    public InvalidBookIdException(String message) {
        super(message);
    }

    public InvalidBookIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
