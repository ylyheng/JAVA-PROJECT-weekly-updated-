class Book extends LibraryItem implements Borrowable {
    boolean isborrowed;

    Book(String title, String author, int publicationYear) {
        super(title, author, publicationYear);
        this.isborrowed = false;
    }

    @Override
    public boolean borrow() {
        if (isborrowed) {
            return false;
        }
        isborrowed = true;
        return true;
    }

    @Override
    public boolean returnItem() {
        if (!isborrowed) {
            return false;
        }
        isborrowed = false;
        return true;
    }

    @Override
    public boolean isBorrowed() {
        return isborrowed;
    }

    @Override
    String getItemType() {
        return "Book";
    }
}