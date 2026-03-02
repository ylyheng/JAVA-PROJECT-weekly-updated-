class Main {
    public static void main(String[] args) {

        System.out.println("=== Week 3 Requirement Proofs ===");
        int a = 10;
        int b = a;  // copy primitive
        b = 20;
        System.out.println("F1: Original primitive a = " + a + ", copy b = " + b);
        Book book1 = new Book("Java Basics", "Author A", 2020);
        Book aliasBook = book1;
        aliasBook.title = "Updated Java Basics";
        System.out.println("F2: Same object title = " + book1.title);
        Library lib = new Library(10);
        lib.addBook(book1);
        System.out.println("F3: Array reference check = " + lib.books[0].title);
        System.out.println("F4: Snapshot year = " + book1.publishedYearSnapshot);
        book1.publicationYear = 2030;
        System.out.println("F4: After original change, snapshot stays = " + book1.publishedYearSnapshot);
        Book missing = lib.findBookByTitle("Not Here");
        if (missing == null) {
            System.out.println("Null safety: Book not found");
        }
    }
}
