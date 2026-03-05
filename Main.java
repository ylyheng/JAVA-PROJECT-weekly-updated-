public class Main {
    public static void main(String[] args) {

        // =============================================
        // Week 3: Primitives vs References Demo
        // =============================================
        System.out.println("=== Week 3: Primitives vs References ===\n");

        // F1: Primitive copy - changing copy does NOT affect original
        int a = 10;
        int b = a;
        b = 20;
        System.out.println("F1: Primitive copy — original a = " + a + ", copy b = " + b);

        // F2: Object alias - same reference, change affects both
        Book book1 = new Book("Java Basics", "Author A", 2020);
        Book aliasBook = book1;
        aliasBook.setTitle("Updated Java Basics");
        System.out.println("F2: Object alias — book1.title = " + book1.getTitle());

        // F3: Array holds references — no cast needed, Book extends LibraryItem
        // Library lib = new Library(10);
        // lib.addBook(book1);
        // System.out.println("F3: Array reference check — " + lib.findBookByTitle("Updated Java Basics"));

        // F4: Snapshot (final field) stays unchanged even when original changes
        System.out.println("F4: Snapshot year = " + book1.getPublishedYearSnapshot());
        book1.setPublicationYear(2030);
        System.out.println("F4: After change, snapshot stays = " + book1.getPublishedYearSnapshot());

        // Null safety: searching for a non-existent book
        // LibraryItem missing = lib.findBookByTitle("Not Here");
        // System.out.println("Null safety: " + (missing == null ? "Book not found (null returned)" : missing));

        // =============================================
        // Week 2: Encapsulation Demo
        // =============================================
        System.out.println("\n=== Week 2: Encapsulation ===\n");

        Book book2 = new Book("Clean Code", "Robert Martin", 2008);
        Book book3 = new Book("Design Patterns", "Gang of Four", 1994);
        // lib.addBook(book2);
        // lib.addBook(book3);

        // lib.printAvailableBooks();

        // =============================================
        // Full Borrow / Return Flow with Transactions
        // =============================================
        System.out.println("\n=== Borrow & Return Flow ===\n");

        Member alice = new Member("Alice", 1);
        Member bob = new Member("Bob", 2);

        int txId = 1;

        // Alice borrows book2
        boolean borrowed = alice.borrowBook(book2);
        if (borrowed) {
            Transaction tx = new Transaction(txId++, alice, book2, Transaction.BORROW);
            System.out.println("Logged: " + tx);
        }

        // Bob tries to borrow the same book (already borrowed)
        borrowed = bob.borrowBook(book2);
        if (!borrowed) {
            System.out.println("Bob could not borrow '" + book2.getTitle() + "'.");
        }

        // Bob borrows book3
        borrowed = bob.borrowBook(book3);
        if (borrowed) {
            Transaction tx = new Transaction(txId++, bob, book3, Transaction.BORROW);
            System.out.println("Logged: " + tx);
        }

        System.out.println();
        alice.printBorrowedBooks();
        bob.printBorrowedBooks();

        // Alice returns book2
        System.out.println();
        boolean returned = alice.returnBook(book2);
        if (returned) {
            Transaction tx = new Transaction(txId++, alice, book2, Transaction.RETURN);
            System.out.println("Logged: " + tx);
        }

        System.out.println();
        // lib.printAvailableBooks();
    }
}