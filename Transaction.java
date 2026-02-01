import java.util.Date;

public class Transaction {

    private int transactionId;
    private int bookId;
    private int memberId;
    private Date transactionDate;
    private String transactionType; 

    public Transaction(int transactionId, int bookId, int memberId, String transactionType) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.transactionType = transactionType;
        this.transactionDate = new Date();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        if (transactionType.equals("Issue") || transactionType.equals("Return")) {
            this.transactionType = transactionType;
        }
    }
}

