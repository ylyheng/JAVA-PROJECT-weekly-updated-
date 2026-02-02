public class Book {

    // Private data members
    private int bookId;
    private String title;
    private boolean issued;
    private String author;

    // Constructor
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return issued;
    }

    public String getAuthor() {
        return author;
    }

    public void issueBook() {
        issued = true;
    }

    public void returnBook() {
        issued = false;
    }
}
