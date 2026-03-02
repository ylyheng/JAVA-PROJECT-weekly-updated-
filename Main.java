class Main {
    static void main(String[] args) {
        System.out.println("=== Week 3 Requirement Proofs ===");

        // F1 — Primitive copy
        int a = 10;
        int b = a;
        b = 20;
        System.out.println("F1: Original primitive a = " + a + ", copy b = " + b);

        // Prepare objects
        Book book1 = new Book("Java Basics", "Author A", 2020);

        // F2 — Reference copy
        Book aliasBook = book1;
        aliasBook.title = "Updated Java Basics";
        System.out.println("F2: Same object title = " + book1.title);

        // F3 — Array stores references
        Library lib = new Library(10);
        lib.addBook(book1);
        book1.title = "Java Basics (Second Edition)";
        System.out.println("F3: Array reference check after original change = " + lib.books[0].title);

        // F4 — Snapshot behavior
        System.out.println("F4: Snapshot year = " + book1.publishedYearSnapshot);
        book1.publicationYear = 2030;
        System.out.println("F4: After original change, snapshot stays = " + book1.publishedYearSnapshot);

        // Null safety
        Book missing = lib.findBookByTitle("Not Here");
        if (missing == null) {
            System.out.println("Null safety: Book not found");
        }
    }
}
