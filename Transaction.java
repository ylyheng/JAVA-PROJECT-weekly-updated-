class Transaction {
    Member memberRef;    // object reference
    Book bookRef;        // object reference
    String transactionType;
    int transactionId;

    Transaction(int transactionId, Member m, Book b, String type) {
        this.transactionId = transactionId;
        this.memberRef = m;
        this.bookRef = b;
        this.transactionType = type; // "BORROW" or "RETURN"
    }
}
