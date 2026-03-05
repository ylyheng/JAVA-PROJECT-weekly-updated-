public class Main {
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       LIBRARY MANAGEMENT SYSTEM      ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // --- Setup Library ---
        Library library = new Library(10);
        library.addBook(new Book("Clean Code", "Robert Martin", 2008));
        library.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt", 1999));
        library.addBook(new Book("Design Patterns", "Gang of Four", 1994));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 2018));
        library.addBook(new Book("Java Basics", "Author A", 2020));

        // --- Register Members ---
        Member alice = new Member("Alice", 1);
        Member bob = new Member("Bob", 2);
        Member charlie = new Member("Charlie", 3);

        int txId = 1;

        // =============================================
        // Show all available books
        // =============================================
        System.out.println("📚 All Available Books:");
        System.out.println("────────────────────────────────────────");
        library.printAvailableBooks();

        // =============================================
        // Members borrow books
        // =============================================
        System.out.println("\n👤 Member Borrowing Books:");
        System.out.println("────────────────────────────────────────");

        borrowAndLog(alice,   library.findBookByTitle("Clean Code"),             txId++);
        borrowAndLog(alice,   library.findBookByTitle("Effective Java"),         txId++);
        borrowAndLog(bob,     library.findBookByTitle("Design Patterns"),        txId++);
        borrowAndLog(bob,     library.findBookByTitle("Clean Code"),             txId++); // already borrowed
        borrowAndLog(charlie, library.findBookByTitle("Java Basics"),            txId++);

        // =============================================
        // Show borrowed books per member
        // =============================================
        System.out.println("\n📋 Borrowed Books Per Member:");
        System.out.println("────────────────────────────────────────");
        alice.printBorrowedBooks();
        bob.printBorrowedBooks();
        charlie.printBorrowedBooks();

        // =============================================
        // Show remaining available books
        // =============================================
        System.out.println("\n📚 Currently Available Books:");
        System.out.println("────────────────────────────────────────");
        library.printAvailableBooks();

        // =============================================
        // Members return books
        // =============================================
        System.out.println("\n🔄 Returning Books:");
        System.out.println("────────────────────────────────────────");

        returnAndLog(alice, library.findBookByTitle("Clean Code"),      txId++);
        returnAndLog(bob,   library.findBookByTitle("Design Patterns"), txId++);

        // =============================================
        // Final available books
        // =============================================
        System.out.println("\n📚 Available Books After Returns:");
        System.out.println("────────────────────────────────────────");
        library.printAvailableBooks();

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         END OF SESSION               ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // Helper: borrow and log transaction
    static void borrowAndLog(Member member, Book book, int txId) {
        if (book == null) return;
        boolean success = member.borrowBook(book);
        if (success) {
            System.out.println("  [TX-" + txId + "] " + member.getName() +
                               " borrowed '" + book.getTitle() + "' ✅");
            new Transaction(txId, member, book, Transaction.BORROW);
        } else {
            System.out.println("  [TX-" + txId + "] " + member.getName() +
                               " could not borrow '" + book.getTitle() + "' ❌");
        }
    }

    // Helper: return and log transaction
    static void returnAndLog(Member member, Book book, int txId) {
        if (book == null) return;
        boolean success = member.returnBook(book);
        if (success) {
            System.out.println("  [TX-" + txId + "] " + member.getName() +
                               " returned '" + book.getTitle() + "' ✅");
            new Transaction(txId, member, book, Transaction.RETURN);
        } else {
            System.out.println("  [TX-" + txId + "] " + member.getName() +
                               " could not return '" + book.getTitle() + "' ❌");
        }
    }
}