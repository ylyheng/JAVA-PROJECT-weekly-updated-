public class Library {
    private LibraryItem[] books; // uses abstract base type
    private int bookCount;

    public Library(int capacity) {
        this.books = new LibraryItem[capacity];
        this.bookCount = 0;
    }

    // Add any LibraryItem (Book, Magazine, DVD, etc.)
    public boolean addBook(LibraryItem item) {
        if (item == null) return false;
        if (bookCount >= books.length) {
            System.out.println("Library is full. Cannot add '" + item.getTitle() + "'.");
            return false;
        }
        books[bookCount] = item;
        bookCount++;
        return true;
    }

    // Find by title
    public LibraryItem findBookByTitle(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getTitle().equals(title)) {
                return books[i];
            }
        }
        return null;
    }

    // Find by author
    public LibraryItem findBookByAuthor(String author) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getAuthor().equals(author)) {
                return books[i];
            }
        }
        return null;
    }

    // Display all available (not borrowed) items
    public void printAvailableBooks() {
        System.out.println("Available Items:");
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] instanceof Borrowable) {
                Borrowable item = (Borrowable) books[i];
                if (!item.isBorrowed()) {
                    System.out.println("  - " + books[i]);
                    found = true;
                }
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