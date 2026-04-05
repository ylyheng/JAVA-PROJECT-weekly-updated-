package src.model;
import java.util.ArrayList;

/**
 * Member class - represents a library member
 * Inherits from User and demonstrates inheritance
 * Members can borrow books and accumulate fines
 */
public class Member extends User {
    // Additional fields specific to members
    private ArrayList<Book> borrowedBooks;
    private double fineAmount;
    private String membershipDate;

    // Constructor
    public Member(int userId, String name, String email, String membershipDate) {
        super(userId, name, email); // Call parent constructor
        this.borrowedBooks = new ArrayList<>();
        this.fineAmount = 0.0;
        this.membershipDate = membershipDate;
    }

    // Getters and Setters
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    /**
     * Add fine to membership (for late returns)
     * @param amount - fine amount to add
     */
    public void addFine(double amount) {
        this.fineAmount += amount;
    }

    /**
     * Pay fine amount
     * @param amount - amount to pay
     * @return true if payment successful, false if amount exceeds fine
     */
    public boolean payFine(double amount) {
        if (amount <= fineAmount) {
            fineAmount -= amount;
            return true;
        }
        return false;
    }

    /**
     * Borrow a book
     * @param book - book to borrow
     * @return true if successful, false if already borrowed
     */
    public boolean borrowBook(Book book) {
        if (book != null && !book.isBorrowed()) {
            book.borrow();
            borrowedBooks.add(book);
            return true;
        }
        return false;
    }

    /**
     * Return a book
     * @param book - book to return
     * @return true if successful, false if book not in borrowed list
     */
    public boolean returnBook(Book book) {
        if (book != null && borrowedBooks.contains(book)) {
            book.returnItem();
            borrowedBooks.remove(book);
            return true;
        }
        return false;
    }

    /**
     * Get number of books borrowed
     */
    public int getNumberOfBorrowedBooks() {
        return borrowedBooks.size();
    }

    /**
     * Polymorphic method - override from User
     */
    @Override
    public String getRole() {
        return "Member";
    }

    /**
     * Polymorphic method - override from User
     */
    @Override
    public void displayMenu() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║         MEMBER MENU                    ║");
        System.out.println("     ╠════════════════════════════════════════╣");
        System.out.println("     ║  1. Browse Available Books             ║");
        System.out.println("     ║  2. Borrow a Book                      ║");
        System.out.println("     ║  3. View My Borrowed Books             ║");
        System.out.println("     ║  4. View Fine Amount                   ║");
        System.out.println("     ║  5. Pay Fine                           ║");
        System.out.println("     ║  6. Exit                               ║");
        System.out.println("     ╚════════════════════════════════════════╝");
    }

    @Override
    public String toString() {
        return "Member{id=" + userId + ", name='" + name + "', email='" + email +
               "', membershipDate='" + membershipDate + "', borrowedBooks=" + borrowedBooks.size() +
               ", fine=$" + String.format("%.2f", fineAmount) + "}";
    }
}
