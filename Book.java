/**
 * Book class - represents a library book
 * Implements Borrowable interface to support borrowing functionality
 * Demonstrates encapsulation with private fields and public getters/setters
 */
public class Book implements Borrowable {
    // Private fields - encapsulation
    private int bookId;
    private String title;
    private String author;
    private int publicationYear;
    private boolean isBorrowed;
    private final int publishedYearSnapshot; // snapshot is immutable (final)

    // Constructor
    public Book(int bookId, String title, String author, int publicationYear) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isBorrowed = false;
        this.publishedYearSnapshot = publicationYear; // captured once, never changes
    }

    // Getters
    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isBorrowed() { return isBorrowed; }
    public int getPublishedYearSnapshot() { return publishedYearSnapshot; }

    // Setters (only for fields that should be changeable)
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    // Implementation of Borrowable interface methods
    @Override
    public boolean borrow() {
        if (!isBorrowed) {
            isBorrowed = true;
            return true;
        }
        return false; // Already borrowed
    }

    @Override
    public boolean returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
            return true;
        }
        return false; // Not borrowed
    }

    @Override
    public String toString() {
        return "Book{id=" + bookId + ", title='" + title + "', author='" + author +
               "', year=" + publicationYear + ", available=" + !isBorrowed + "}";
    }
}