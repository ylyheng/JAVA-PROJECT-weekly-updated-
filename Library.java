import java.util.ArrayList;

/**
 * Library class - manages all library operations
 * Demonstrates encapsulation by managing books, members, and transactions
 * Uses ArrayList for dynamic storage
 */
public class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private ArrayList<Transaction> transactions;
    private int nextBookId;
    private int nextTransactionId;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.nextBookId = 1;
        this.nextTransactionId = 1;
    }

    /**
     * Add a book to the library
     * @param book - book to add
     * @return true if successful, false if null
     */
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        books.add(book);
        System.out.println("     ✓ Book '" + book.getTitle() + "' added successfully!");
        return true;
    }

    /**
     * Add a member to the library
     * @param member - member to add
     * @return true if successful
     */
    public boolean addMember(Member member) {
        if (member == null) {
            return false;
        }
        members.add(member);
        System.out.println("     ✓ Member '" + member.getName() + "' added successfully!");
        return true;
    }

    /**
     * Find book by title (case-insensitive)
     * @param title - title to search
     * @return Book if found, null otherwise
     */
    public Book findBookByTitle(String title) {
        if (title == null) return null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Find book by ID
     * @param bookId - book ID to search
     * @return Book if found, null otherwise
     */
    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    /**
     * Find book by author (case-insensitive)
     * @param author - author to search
     * @return Book if found, null otherwise
     */
    public Book findBookByAuthor(String author) {
        if (author == null) return null;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Find member by ID
     * @param memberId - member ID to search
     * @return Member if found, null otherwise
     */
    public Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getUserId() == memberId) {
                return member;
            }
        }
        return null;
    }

    /**
     * Find member by name
     * @param name - name to search
     * @return Member if found, null otherwise
     */
    public Member findMemberByName(String name) {
        if (name == null) return null;
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }

    /**
     * Issue a book to a member
     * @param member - member borrowing the book
     * @param book - book to issue
     * @return true if successful
     */
    public boolean issueBook(Member member, Book book) {
        if (member == null || book == null) {
            return false;
        }

        if (member.borrowBook(book)) {
            Transaction transaction = new Transaction(nextTransactionId++, member, book, Transaction.BORROW);
            transactions.add(transaction);
            System.out.println("\n     ✓ Book '" + book.getTitle() + "' issued to " + member.getName());
            return true;
        }
        System.out.println("\n     ✗ Book is already borrowed or not available");
        return false;
    }

    /**
     * Accept book return from member
     * @param member - member returning the book
     * @param book - book to return
     * @param daysLate - number of days late (for fine calculation)
     * @return true if successful
     */
    public boolean returnBook(Member member, Book book, int daysLate) {
        if (member == null || book == null) {
            return false;
        }

        if (member.returnBook(book)) {
            Transaction transaction = new Transaction(nextTransactionId++, member, book, Transaction.RETURN);
            transactions.add(transaction);
            
            // Calculate fine (e.g., $1 per day late)
            if (daysLate > 0) {
                double fineAmount = daysLate * 1.0;
                member.addFine(fineAmount);
                System.out.println("\n     ✓ Book '" + book.getTitle() + "' returned by " + member.getName());
                System.out.println("     ⚠ Late by " + daysLate + " days. Fine applied: $" + String.format("%.2f", fineAmount));
            } else {
                System.out.println("\n     ✓ Book '" + book.getTitle() + "' returned by " + member.getName() + " on time! 👍");
            }
            return true;
        }
        System.out.println("\n     ✗ Book was not borrowed by this member");
        return false;
    }

    /**
     * Get all available (not borrowed) books
     * @return ArrayList of available books
     */
    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    /**
     * Get all borrowed books
     * @return ArrayList of borrowed books
     */
    public ArrayList<Book> getBorrowedBooks() {
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    /**
     * Display all available books
     */
    public void displayAvailableBooks() {
        ArrayList<Book> availableBooks = getAvailableBooks();
        System.out.println("\n     ════════════════════════════════════════");
        System.out.println("         AVAILABLE BOOKS IN LIBRARY");
        System.out.println("     ════════════════════════════════════════");
        if (availableBooks.isEmpty()) {
            System.out.println("     → No books available at the moment.");
        } else {
            for (Book book : availableBooks) {
                System.out.println("     " + book);
            }
        }
        System.out.println("     ════════════════════════════════════════");
    }

    /**
     * Display all borrowed books
     */
    public void displayBorrowedBooks() {
        ArrayList<Book> borrowedBooks = getBorrowedBooks();
        System.out.println("\n     ════════════════════════════════════════");
        System.out.println("         BORROWED BOOKS IN LIBRARY");
        System.out.println("     ════════════════════════════════════════");
        if (borrowedBooks.isEmpty()) {
            System.out.println("     → No books are currently borrowed.");
        } else {
            for (Book book : borrowedBooks) {
                System.out.println("     " + book);
            }
        }
        System.out.println("     ════════════════════════════════════════");
    }

    /**
     * Display all books in library
     */
    public void displayAllBooks() {
        System.out.println("\n     ════════════════════════════════════════");
        System.out.println("         ALL BOOKS IN LIBRARY");
        System.out.println("     ════════════════════════════════════════");
        if (books.isEmpty()) {
            System.out.println("     → Library has no books.");
        } else {
            for (Book book : books) {
                System.out.println("     " + book);
            }
        }
        System.out.println("     ════════════════════════════════════════");
        System.out.println("     Total books: " + books.size() + " | Available: " + 
                          getAvailableBooks().size() + " | Borrowed: " + getBorrowedBooks().size());
        System.out.println("     ════════════════════════════════════════");
    }

    /**
     * Display all members
     */
    public void displayAllMembers() {
        System.out.println("\n     ════════════════════════════════════════");
        System.out.println("         ALL REGISTERED MEMBERS");
        System.out.println("     ════════════════════════════════════════");
        if (members.isEmpty()) {
            System.out.println("     → No members registered.");
        } else {
            for (Member member : members) {
                System.out.println("     " + member);
            }
        }
        System.out.println("     ════════════════════════════════════════");
        System.out.println("     Total members: " + members.size());
        System.out.println("     ════════════════════════════════════════");
    }

    /**
     * Display all transactions
     */
    public void displayTransactions() {
        System.out.println("\n     ════════════════════════════════════════");
        System.out.println("         TRANSACTION HISTORY");
        System.out.println("     ════════════════════════════════════════");
        if (transactions.isEmpty()) {
            System.out.println("     → No transactions recorded.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println("     " + transaction);
            }
        }
        System.out.println("     ════════════════════════════════════════");
        System.out.println("     Total transactions: " + transactions.size());
        System.out.println("     ════════════════════════════════════════");
    }

    // Getters
    public int getTotalBooks() { return books.size(); }
    public int getTotalMembers() { return members.size(); }
    public int getTotalTransactions() { return transactions.size(); }
    public int getNextTransactionId() { return nextTransactionId; }

    /**
     * Add transaction to history
     */
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
            nextTransactionId++;
        }
    }

    @Override
    public String toString() {
        return "Library{totalBooks=" + books.size() + ", totalMembers=" + members.size() +
               ", totalTransactions=" + transactions.size() + "}";
    }
}