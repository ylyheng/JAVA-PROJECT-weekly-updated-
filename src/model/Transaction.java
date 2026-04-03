package src.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction class - records book borrowing and returning transactions
 * Demonstrates how to track member interactions with books
 */
public class Transaction {
    // Transaction types as constants
    public static final String BORROW = "BORROW";
    public static final String RETURN = "RETURN";

    private int transactionId;
    private Member memberRef;
    private Book bookRef;
    private String transactionType;
    private LocalDateTime transactionDate;

    // Constructor
    public Transaction(int transactionId, Member member, Book book, String type) {
        this.transactionId = transactionId;
        this.memberRef = member;
        this.bookRef = book;
        this.transactionType = type;
        this.transactionDate = LocalDateTime.now();
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public Member getMember() {
        return memberRef;
    }

    public Book getBook() {
        return bookRef;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        String memberName = (memberRef != null) ? memberRef.getName() : "Unknown";
        String bookTitle = (bookRef != null) ? bookRef.getTitle() : "Unknown";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateString = transactionDate.format(formatter);

        return "Transaction{id=" + transactionId +
               ", type='" + transactionType +
               "', member='" + memberName +
               "', book='" + bookTitle +
               "', date='" + dateString + "'}";
    }
}