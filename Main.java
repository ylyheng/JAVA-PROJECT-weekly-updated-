import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class - Entry point for Library Management System
 * Demonstrates all OOP concepts: Encapsulation, Inheritance, Polymorphism, Abstraction
 * Menu-driven console application
 */
public class Main {
    private static Library library;
    private static Scanner scanner;
    private static User currentUser;
    private static ArrayList<Librarian> librarians;
    private static ArrayList<Member> allMembers;

    public static void main(String[] args) {
        // Initialize the system
        library = new Library();
        scanner = new Scanner(System.in);
        librarians = new ArrayList<>();
        allMembers = new ArrayList<>();

        // Initialize sample data
        initializeSystemData();

        // Display welcome message
        displayWelcome();

        // Main menu loop
        boolean running = true;
        while (running) {
            if (currentUser == null) {
                running = loginMenu();
            } else {
                if (currentUser instanceof Librarian) {
                    running = librarianMenu();
                } else if (currentUser instanceof Member) {
                    running = memberMenu();
                }
            }
        }

        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║                                        ║");
        System.out.println("     ║  Thank you for using Library System!   ║");
        System.out.println("     ║            Goodbye! 👋                 ║");
        System.out.println("     ║                                        ║");
        System.out.println("     ╚════════════════════════════════════════╝");
        System.out.println();
        scanner.close();
    }

    /**
     * Initialize sample library data for demonstration
     */
    private static void initializeSystemData() {
        // Add default librarian account
        Librarian lib1 = new Librarian(1001, "Admin", "admin@library.com", "EMP001", "Main Branch", 50000);
        librarians.add(lib1);

        // Add default member account
        Member mem1 = new Member(2001, "User", "user@example.com", "2024-04-04");
        allMembers.add(mem1);
        library.addMember(mem1);

        System.out.println("     ✓ System initialized");
    }

    /**
     * Display welcome message
     */
    private static void displayWelcome() {
        System.out.println("\n");
        System.out.println("     ╔════════════════════════════════════════╗");
        System.out.println("     ║                                        ║");
        System.out.println("     ║  WELCOME TO LIBRARY MANAGEMENT SYSTEM  ║");
        System.out.println("     ║      OOP-Based Java Application        ║");
        System.out.println("     ║                                        ║");
        System.out.println("     ╚════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Login menu - authenticate users
     */
    private static boolean loginMenu() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║          LOGIN MENU                    ║");
        System.out.println("     ╠════════════════════════════════════════╣");
        System.out.println("     ║  1. Login as Librarian                 ║");
        System.out.println("     ║  2. Login as Member                    ║");
        System.out.println("     ║  3. Register New Member                ║");
        System.out.println("     ║  4. Exit                               ║");
        System.out.println("     ╚════════════════════════════════════════╝");
        System.out.print("\n     → Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    librarianLogin();
                    break;
                case 2:
                    memberLogin();
                    break;
                case 3:
                    registerNewMember();
                    break;
                case 4:
                    return false; // Exit application
                default:
                    System.out.println("\n     ✗ Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid input. Please enter a number.");
        }
        return true;
    }

    /**
     * Librarian login logic
     */
    private static void librarianLogin() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║    LIBRARIAN LOGIN                     ║");
        System.out.println("     ╚════════════════════════════════════════╝");
        System.out.print("     → Librarian ID: ");
        try {
            int librarianId = Integer.parseInt(scanner.nextLine());
            System.out.print("     → Password: ");
            String password = scanner.nextLine();

            // Simple password check for demo (Alice: pass1, Bob: pass2)
            if ((librarianId == 1001 && password.equals("pass1")) ||
                (librarianId == 1002 && password.equals("pass2"))) {
                
                for (Librarian lib : librarians) {
                    if (lib.getUserId() == librarianId) {
                        currentUser = lib;
                        System.out.println("\n     ✓ Login successful! Welcome, " + lib.getName() + "!");
                        return;
                    }
                }
            } else {
                System.out.println("\n     ✗ Invalid credentials. Login failed.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid input. Please enter a valid ID.");
        }
    }

    /**
     * Member login logic
     */
    private static void memberLogin() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║      MEMBER LOGIN                      ║");
        System.out.println("     ╚════════════════════════════════════════╝");
        System.out.print("     → Member ID: ");
        try {
            int memberId = Integer.parseInt(scanner.nextLine());
            System.out.print("     → PIN (default: 1234): ");
            String pin = scanner.nextLine();

            if (pin.equals("1234")) {
                for (Member member : allMembers) {
                    if (member.getUserId() == memberId) {
                        currentUser = member;
                        System.out.println("\n     ✓ Login successful! Welcome, " + member.getName() + "!");
                        return;
                    }
                }
                System.out.println("\n     ✗ Member ID not found.");
            } else {
                System.out.println("\n     ✗ Invalid PIN.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid input. Please enter a valid ID.");
        }
    }

    /**
     * Register new member
     */
    private static void registerNewMember() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║       MEMBER REGISTRATION               ║");
        System.out.println("     ╚════════════════════════════════════════╝");

        System.out.print("     → Member Name: ");
        String name = scanner.nextLine();

        System.out.print("     → Email: ");
        String email = scanner.nextLine();

        int newMemberId = 2000 + allMembers.size() + 1;
        Member newMember = new Member(newMemberId, name, email, "2024-04-04");
        
        allMembers.add(newMember);
        library.addMember(newMember);

        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║   ✓ Registration successful!            ║");
        System.out.println("     ╠════════════════════════════════════════╣");
        System.out.println("     ║  Your Member ID: " + String.format("%-21d", newMemberId) + "║");
        System.out.println("     ║  Your PIN: 1234 (default)              ║");
        System.out.println("     ║  Please save this information.          ║");
        System.out.println("     ╚════════════════════════════════════════╝");
    }

    /**
     * Librarian menu - displays librarian options
     */
    private static boolean librarianMenu() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║       LIBRARIAN MENU                   ║");
        System.out.println("     ╠════════════════════════════════════════╣");
        System.out.println("     ║  1.  Add New Book                      ║");
        System.out.println("     ║  2.  View All Books                    ║");
        System.out.println("     ║  3.  Search Book by Title              ║");
        System.out.println("     ║  4.  Search Book by ID                 ║");
        System.out.println("     ║  5.  Search Book by Author             ║");
        System.out.println("     ║  6.  Issue Book to Member              ║");
        System.out.println("     ║  7.  Accept Book Return                ║");
        System.out.println("     ║  8.  View All Members                  ║");
        System.out.println("     ║  9.  View Transactions                 ║");
        System.out.println("     ║  10. Exit                              ║");
        System.out.println("     ╚════════════════════════════════════════╝");
        System.out.print("\n     → Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    library.displayAllBooks();
                    break;
                case 3:
                    searchBookByTitle();
                    break;
                case 4:
                    searchBookById();
                    break;
                case 5:
                    searchBookByAuthor();
                    break;
                case 6:
                    issueBookToMember();
                    break;
                case 7:
                    acceptBookReturn();
                    break;
                case 8:
                    library.displayAllMembers();
                    break;
                case 9:
                    library.displayTransactions();
                    break;
                case 10:
                    currentUser = null; // Logout
                    System.out.println("\n     ✓ Logged out successfully.");
                    break;
                default:
                    System.out.println("\n     ✗ Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid input. Please enter a number.");
        }
        return true;
    }

    /**
     * Member menu - displays member options
     */
    private static boolean memberMenu() {
        currentUser.displayMenu();
        System.out.print("\n     → Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            Member member = (Member) currentUser;

            switch (choice) {
                case 1:
                    browseAvailableBooks();
                    break;
                case 2:
                    memberBorrowBook(member);
                    break;
                case 3:
                    displayBorrowedBooks(member);
                    break;
                case 4:
                    displayMemberFine(member);
                    break;
                case 5:
                    payFine(member);
                    break;
                case 6:
                    currentUser = null; // Logout
                    System.out.println("\n     ✓ Logged out successfully.");
                    break;
                default:
                    System.out.println("\n     ✗ Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid input. Please enter a number.");
        }
        return true;
    }

    /**
     * Add a new book to the library (Librarian only)
     */
    private static void addNewBook() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║          ADD NEW BOOK                   ║");
        System.out.println("     ╚════════════════════════════════════════╝");

        System.out.print("     → Book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        System.out.print("     → Book Title: ");
        String title = scanner.nextLine();

        System.out.print("     → Author: ");
        String author = scanner.nextLine();

        System.out.print("     → Publication Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        Book newBook = new Book(bookId, title, author, year);
        library.addBook(newBook);
    }

    /**
     * Search book by title
     */
    private static void searchBookByTitle() {
        System.out.print("\n     → Enter book title to search: ");
        String title = scanner.nextLine();

        Book book = library.findBookByTitle(title);
        if (book != null) {
            System.out.println("\n     ✓ Book found: " + book);
        } else {
            System.out.println("\n     ✗ Book not found.");
        }
    }

    /**
     * Search book by ID
     */
    private static void searchBookById() {
        System.out.print("\n     → Enter book ID to search: ");
        try {
            int bookId = Integer.parseInt(scanner.nextLine());
            Book book = library.findBookById(bookId);
            if (book != null) {
                System.out.println("\n     ✓ Book found: " + book);
            } else {
                System.out.println("\n     ✗ Book not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid ID format.");
        }
    }

    /**
     * Search book by author
     */
    private static void searchBookByAuthor() {
        System.out.print("\n     → Enter author name to search: ");
        String author = scanner.nextLine();

        Book book = library.findBookByAuthor(author);
        if (book != null) {
            System.out.println("\n     ✓ Book found: " + book);
        } else {
            System.out.println("\n     ✗ Book not found.");
        }
    }

    /**
     * Issue book to member
     */
    private static void issueBookToMember() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║          ISSUE BOOK TO MEMBER           ║");
        System.out.println("     ╚════════════════════════════════════════╝");

        System.out.print("     → Member ID: ");
        try {
            int memberId = Integer.parseInt(scanner.nextLine());
            Member member = library.findMemberById(memberId);

            if (member == null) {
                System.out.println("\n     ✗ Member not found.");
                return;
            }

            System.out.print("     → Book ID: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            Book book = library.findBookById(bookId);

            if (book == null) {
                System.out.println("\n     ✗ Book not found.");
                return;
            }

            library.issueBook(member, book);
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid input.");
        }
    }

    /**
     * Accept book return from member
     */
    private static void acceptBookReturn() {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║          ACCEPT BOOK RETURN             ║");
        System.out.println("     ╚════════════════════════════════════════╝");

        System.out.print("     → Member ID: ");
        try {
            int memberId = Integer.parseInt(scanner.nextLine());
            Member member = library.findMemberById(memberId);

            if (member == null) {
                System.out.println("\n     ✗ Member not found.");
                return;
            }

            System.out.print("     → Book ID: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            Book book = library.findBookById(bookId);

            if (book == null) {
                System.out.println("\n     ✗ Book not found.");
                return;
            }

            System.out.print("     → Days late (0 if on time): ");
            int daysLate = Integer.parseInt(scanner.nextLine());

            library.returnBook(member, book, daysLate);
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid input.");
        }
    }

    /**
     * Browse available books for members
     */
    private static void browseAvailableBooks() {
        ArrayList<Book> availableBooks = library.getAvailableBooks();
        System.out.println("\n     ════════════════════════════════════════");
        System.out.println("         AVAILABLE BOOKS FOR BORROWING");
        System.out.println("     ════════════════════════════════════════");
        if (availableBooks.isEmpty()) {
            System.out.println("     → Sorry, no books are currently available.");
        } else {
            for (Book book : availableBooks) {
                System.out.println("     " + book);
            }
        }
        System.out.println("     ════════════════════════════════════════");
    }

    /**
     * Member borrows a book
     */
    private static void memberBorrowBook(Member member) {
        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║          BORROW A BOOK                  ║");
        System.out.println("     ╚════════════════════════════════════════╝");

        System.out.print("     → Enter Book ID: ");
        try {
            int bookId = Integer.parseInt(scanner.nextLine());
            Book book = library.findBookById(bookId);

            if (book == null) {
                System.out.println("\n     ✗ Book not found.");
                return;
            }

            if (book.isBorrowed()) {
                System.out.println("\n     ✗ Sorry, this book is already borrowed.");
                return;
            }

            if (member.borrowBook(book)) {
                Transaction transaction = new Transaction(library.getNextTransactionId(), member, book, Transaction.BORROW);
                library.addTransaction(transaction);
                System.out.println("\n     ✓ Book '" + book.getTitle() + "' borrowed successfully!");
                System.out.println("     → Remember to return it on time to avoid fines.");
            } else {
                System.out.println("\n     ✗ Error borrowing book. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid Book ID format.");
        }
    }

    /**
     * Display books borrowed by member
     */
    private static void displayBorrowedBooks(Member member) {
        ArrayList<Book> books = member.getBorrowedBooks();
        if (books.isEmpty()) {
            System.out.println("\n     → You have not borrowed any books.");
        } else {
            System.out.println("\n     ════════════════════════════════════════");
            System.out.println("               YOUR BORROWED BOOKS");
            System.out.println("     ════════════════════════════════════════");
            for (Book book : books) {
                System.out.println("     " + book);
            }
            System.out.println("     ════════════════════════════════════════");
            System.out.println("     Total borrowed: " + books.size());
        }
    }

    /**
     * Display member's current fine
     */
    private static void displayMemberFine(Member member) {
        System.out.println("\n     ════════════════════════════════════════");
        System.out.println("           FINE INFORMATION");
        System.out.println("     ════════════════════════════════════════");
        System.out.println("     Current Fine: $" + String.format("%.2f", member.getFineAmount()));
        if (member.getFineAmount() > 0) {
            System.out.println("     ⚠ Please pay your fine to continue");
            System.out.println("       borrowing books.");
        } else {
            System.out.println("     ✓ No pending fines!");
        }
        System.out.println("     ════════════════════════════════════════");
    }

    /**
     * Pay fine for late returns
     */
    private static void payFine(Member member) {
        if (member.getFineAmount() <= 0) {
            System.out.println("\n     → No pending fines to pay.");
            return;
        }

        System.out.println("\n     ╔════════════════════════════════════════╗");
        System.out.println("     ║          PAY FINE                       ║");
        System.out.println("     ╚════════════════════════════════════════╝");
        System.out.println("     Current Fine: $" + String.format("%.2f", member.getFineAmount()));
        System.out.print("     → Enter amount to pay: $");

        try {
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("\n     ✗ Invalid amount. Must be greater than 0.");
                return;
            }

            if (member.payFine(amount)) {
                System.out.println("\n     ✓ Payment of $" + String.format("%.2f", amount) + " successful!");
                System.out.println("     ✓ Remaining fine: $" + String.format("%.2f", member.getFineAmount()));
            } else {
                System.out.println("\n     ✗ Payment amount exceeds fine.");
                System.out.println("     ✗ Fine: $" + String.format("%.2f", member.getFineAmount()));
            }
        } catch (NumberFormatException e) {
            System.out.println("\n     ✗ Invalid amount format.");
        }
    }
}