public class Member {
    private static final int MAX_BORROWED_BOOKS = 5; // named constant, not magic number

    private String name;
    private int memberId;
    private Book[] borrowedBooks;
    private int borrowedBooksCount;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new Book[MAX_BORROWED_BOOKS];
        this.borrowedBooksCount = 0;
    }

    // Borrow a book - accepts Borrowable interface type
    public boolean borrowBook(Book b) {
        if (b == null) return false;
        if (b.isBorrowed()) {
            System.out.println("Book '" + b.getTitle() + "' is already borrowed.");
            return false;
        }
        if (borrowedBooksCount >= MAX_BORROWED_BOOKS) {
            System.out.println(name + " has reached the borrow limit of " + MAX_BORROWED_BOOKS + ".");
            return false;
        }
        b.setBorrowed(true); // calls Borrowable interface method
        borrowedBooks[borrowedBooksCount] = b;
        borrowedBooksCount++;
        return true;
    }

    // Return a book - calls Borrowable interface method
    public boolean returnBook(Book b) {
        if (b == null) return false;
        for (int i = 0; i < borrowedBooksCount; i++) {
            if (borrowedBooks[i] == b) {
                b.setBorrowed(false); // calls Borrowable interface method
                for (int j = i; j < borrowedBooksCount - 1; j++) {
                    borrowedBooks[j] = borrowedBooks[j + 1];
                }
                borrowedBooks[borrowedBooksCount - 1] = null;
                borrowedBooksCount--;
                return true;
            }
        }
        System.out.println("Book '" + b.getTitle() + "' not found in " + name + "'s borrowed list.");
        return false;
    }

    // Getters
    public String getName() { return name; }
    public int getMemberId() { return memberId; }
    public int getBorrowedBooksCount() { return borrowedBooksCount; }

    public void printBorrowedBooks() {
        if (borrowedBooksCount == 0) {
            System.out.println(name + " has no borrowed books.");
            return;
        }
        System.out.println(name + "'s borrowed books:");
        for (int i = 0; i < borrowedBooksCount; i++) {
            System.out.println("  " + (i + 1) + ". " + borrowedBooks[i]);
        }
    }

    @Override
    public String toString() {
        return "Member{name='" + name + "', id=" + memberId +
               ", borrowedCount=" + borrowedBooksCount + "}";
    }
}