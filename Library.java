class Library {
    Book[] books;
    int bookCount;

    Library(int capacity) {
        books = new Book[capacity];
        bookCount = 0;
    }

    void addBook(Book b) {
        if (b != null && bookCount < books.length) {
            books[bookCount] = b;
            bookCount++;
        }
    }

    Book findBookByTitle(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].title.equals(title)) {
                return books[i];
            }
        }
        return null;
    }
}
