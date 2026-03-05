public class Book {
    // Private fields - encapsulation
    private String title;
    private String author;
    private int publicationYear;
    private boolean isBorrowed;
    private final int publishedYearSnapshot; // snapshot is immutable (final)

    // Constructor
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isBorrowed = false;
        this.publishedYearSnapshot = publicationYear; // captured once, never changes
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isBorrowed() { return isBorrowed; }
    public int getPublishedYearSnapshot() { return publishedYearSnapshot; }

    // Setters (only for fields that should be changeable)
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
    public void setBorrowed(boolean borrowed) { this.isBorrowed = borrowed; }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author +
               "', year=" + publicationYear + ", borrowed=" + isBorrowed + "}";
    }
}