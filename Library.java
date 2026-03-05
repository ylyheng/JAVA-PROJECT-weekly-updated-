public class Library {
    private Book[] books;
    private int bookCount;

    public Library(int capacity) {
        this.books = new Book[capacity];
        this.bookCount = 0;
    }

    // Add a book to the library
    public boolean addBook(Book item) {
        if (item == null) return false;
        if (bookCount >= books.length) {
            System.out.println("Library is full. Cannot add '" + item.getTitle() + "'.");
            return false;
        }
        books[bookCount] = item;
        bookCount++;
        return true;
    }

    // Find by title - returns Book directly
    public Book findBookByTitle(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getTitle().equals(title)) {
                return books[i];
            }
        }
        return null;
    }

    // Find by author
    public Book findBookByAuthor(String author) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getAuthor().equals(author)) {
                return books[i];
            }
        }
        return null;
    }

    // Display all available (not borrowed) books
    public void printAvailableBooks() {
        System.out.println("Available Items:");
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && !books[i].isBorrowed()) {
                System.out.println("  - " + books[i]);
                found = true;
            }
        }
        if (!found) System.out.println("  No items currently available.");
    }

    public int getBookCount() { return bookCount; }

    @Override
    public String toString() {
        return "Library{totalItems=" + bookCount + "}";
    }
}