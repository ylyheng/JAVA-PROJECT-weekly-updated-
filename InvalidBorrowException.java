/**
 * Custom Exception for invalid borrowing operations
 */
public class InvalidBorrowException extends Exception {
    public InvalidBorrowException(String message) {
        super(message);
    }

    public InvalidBorrowException(String message, Throwable cause) {
        super(message, cause);
    }
}
