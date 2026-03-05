public class Transaction {
    // Transaction types as constants
    public static final String BORROW = "BORROW";
    public static final String RETURN = "RETURN";

    private int transactionId;
    private Member memberRef;
    private Book bookRef;
    private String transactionType;

    public Transaction(int transactionId, Member m, Book b, String type) {
        this.transactionId = transactionId;
        this.memberRef = m;
        this.bookRef = b;
        this.transactionType = type;
    }

    // Getters
    public int getTransactionId() { return transactionId; }
    public Member getMember() { return memberRef; }
    public Book getBook() { return bookRef; }
    public String getTransactionType() { return transactionType; }

    @Override
    public String toString() {
        return "Transaction{id=" + transactionId +
               ", type='" + transactionType +
               "', member='" + memberRef.getName() +
               "', book='" + bookRef.getTitle() + "'}";
    }
}