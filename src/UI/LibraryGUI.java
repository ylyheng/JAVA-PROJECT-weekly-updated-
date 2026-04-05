package src.UI;
import javax.swing.*;

import src.model.Book;
import src.model.Member;
import src.model.User;
import src.service.Librarian;
import src.service.Library;

import java.awt.*;
import java.util.ArrayList;

/**
 * LibraryGUI - Basic Graphical User Interface for Library Management System
 * Simple Swing application with login and menu navigation
 */
public class LibraryGUI extends JFrame {
    private Library library;
    private User currentUser;
    private ArrayList<Librarian> librarians;
    private ArrayList<Member> allMembers;

    // GUI Components
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton submitButton;

    public LibraryGUI() {
        // Initialize library system
        library = new Library();
        librarians = new ArrayList<>();
        allMembers = new ArrayList<>();
        currentUser = null;

        // Initialize default accounts
        initializeSystemData();

        // Setup GUI
        setupGUI();
        showWelcome();
    }

    /**
     * Setup the GUI components - basic version
     */
    private void setupGUI() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        // Main Panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Output Area (text display)
        outputArea = new JTextArea(20, 60);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter your choice:"));
        
        inputField = new JTextField(20);
        submitButton = new JButton("Submit");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);

        // Add event listeners
        submitButton.addActionListener(e -> processInput());
        inputField.addActionListener(e -> processInput());

        // Add components to main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    /**
     * Initialize system data with default accounts
     */
    private void initializeSystemData() {
        Librarian lib1 = new Librarian(1001, "Admin", "admin@library.com", "EMP001", "Main Branch", 50000);
        librarians.add(lib1);

        Member mem1 = new Member(2001, "User", "user@example.com", "2024-04-04");
        allMembers.add(mem1);
        library.addMember(mem1);
    }

    /**
     * Display welcome message
     */
    private void showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append("  LIBRARY MANAGEMENT SYSTEM - GUI\n");
        sb.append("========================================\n\n");
        sb.append("DEFAULT ACCOUNTS:\n");
        sb.append("Librarian ID: 1001, Password: pass1\n");
        sb.append("Member ID: 2001, PIN: 1234\n\n");
        sb.append("ACTIONS:\n");
        sb.append("Type 'L' to login as Librarian\n");
        sb.append("Type 'M' to login as Member\n");
        sb.append("Type 'R' to register as new Member\n");
        sb.append("Type 'Q' to quit\n");
        outputArea.setText(sb.toString());
    }

    /**
     * Process user input
     */
    private void processInput() {
        String input = inputField.getText().trim().toUpperCase();
        inputField.setText("");

        if (currentUser == null) {
            // Not logged in - handle login options
            if (input.equals("L")) {
                librarianLogin();
            } else if (input.equals("M")) {
                memberLogin();
            } else if (input.equals("R")) {
                registerMember();
            } else if (input.equals("Q")) {
                System.exit(0);
            } else {
                appendOutput("Invalid option. Type 'L' for Librarian, 'M' for Member, 'R' to register, or 'Q' to quit.\n");
            }
        } else {
            // Logged in - handle menu actions
            try {
                int choice = Integer.parseInt(input);
                if (currentUser instanceof Librarian) {
                    handleLibrarianAction(choice);
                } else if (currentUser instanceof Member) {
                    handleMemberAction(choice);
                }
            } catch (NumberFormatException e) {
                appendOutput("Please enter a valid number.\n");
            }
        }
    }

    /**
     * Librarian login
     */
    private void librarianLogin() {
        JTextField idField = new JTextField("1001");
        JPasswordField passField = new JPasswordField("pass1");

        Object[] fields = {
                "Librarian ID:", idField,
                "Password:", passField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Librarian Login",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int libId = Integer.parseInt(idField.getText());
                String password = new String(passField.getPassword());

                if (libId == 1001 && password.equals("pass1")) {
                    for (Librarian lib : librarians) {
                        if (lib.getUserId() == libId) {
                            currentUser = lib;
                            showLibrarianMenu();
                            return;
                        }
                    }
                }
                appendOutput("Invalid credentials!\n");
            } catch (NumberFormatException e) {
                appendOutput("Invalid input!\n");
            }
        }
    }

    /**
     * Register new member
     */
    private void registerMember() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] fields = {
                "Name:", nameField,
                "Email:", emailField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Register New Member",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            if (!name.isEmpty() && !email.isEmpty()) {
                // Generate new member ID
                int newMemberId = 2000 + allMembers.size() + 1;
                
                // Create new member
                Member newMember = new Member(newMemberId, name, email, "2024-04-04");
                allMembers.add(newMember);
                library.addMember(newMember);

                // Show success message
                appendOutput("\n" + "=".repeat(40) + "\n");
                appendOutput("✓ REGISTRATION SUCCESSFUL!\n");
                appendOutput("=".repeat(40) + "\n");
                appendOutput("Your Member ID: " + newMemberId + "\n");
                appendOutput("Default PIN: 1234\n");
                appendOutput("Name: " + name + "\n");
                appendOutput("Email: " + email + "\n");
                appendOutput("\nYou can now login with 'L' or 'M'\n");
                appendOutput("=".repeat(40) + "\n");
                showWelcome();
            } else {
                appendOutput("\n✗ Registration failed. Please fill all fields.\n");
                showWelcome();
            }
        } else {
            showWelcome();
        }
    }

    /**
     * Member login
     */
    private void memberLogin() {
        JTextField idField = new JTextField("2001");
        JPasswordField pinField = new JPasswordField("1234");

        Object[] fields = {
                "Member ID:", idField,
                "PIN:", pinField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Member Login",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int memId = Integer.parseInt(idField.getText());
                String pin = new String(pinField.getPassword());

                if (pin.equals("1234")) {
                    for (Member member : allMembers) {
                        if (member.getUserId() == memId) {
                            currentUser = member;
                            showMemberMenu();
                            return;
                        }
                    }
                }
                appendOutput("Invalid Member ID or PIN!\n");
            } catch (NumberFormatException e) {
                appendOutput("Invalid input!\n");
            }
        }
    }

    /**
     * Show librarian menu
     */
    private void showLibrarianMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append("  LIBRARIAN MENU\n");
        sb.append("  User: ").append(currentUser.getName()).append("\n");
        sb.append("========================================\n");
        sb.append("1. Add New Book\n");
        sb.append("2. View All Books\n");
        sb.append("3. Search Book by ID\n");
        sb.append("4. Issue Book to Member\n");
        sb.append("5. Accept Book Return\n");
        sb.append("6. View All Members\n");
        sb.append("7. View Transactions\n");
        sb.append("0. Logout\n");
        sb.append("========================================\n");
        sb.append("Enter your choice (0-7):\n");
        outputArea.setText(sb.toString());
    }

    /**
     * Show member menu
     */
    private void showMemberMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append("  MEMBER MENU\n");
        sb.append("  User: ").append(currentUser.getName()).append("\n");
        sb.append("========================================\n");
        sb.append("1. Browse Available Books\n");
        sb.append("2. Borrow a Book\n");
        sb.append("3. View My Borrowed Books\n");
        sb.append("4. View My Fine Amount\n");
        sb.append("5. Pay Fine\n");
        sb.append("0. Logout\n");
        sb.append("========================================\n");
        sb.append("Enter your choice (0-5):\n");
        outputArea.setText(sb.toString());
    }
    
    /**
     * Append message then show librarian menu
     */
    private void appendAndShowLibrarianMenu(String message) {
        appendOutput(message);
        appendOutput("\n========================================\n");
        appendOutput("  LIBRARIAN MENU - Choose an action\n");
        appendOutput("========================================\n");
        appendOutput("1. Add New Book\n");
        appendOutput("2. View All Books\n");
        appendOutput("3. Search Book by ID\n");
        appendOutput("4. Issue Book to Member\n");
        appendOutput("5. Accept Book Return\n");
        appendOutput("6. View All Members\n");
        appendOutput("7. View Transactions\n");
        appendOutput("0. Logout\n");
        appendOutput("========================================\n");
        appendOutput("Enter your choice (0-7):\n");
    }
    
    /**
     * Append message then show member menu
     */
    private void appendAndShowMemberMenu(String message) {
        appendOutput(message);
        appendOutput("\n========================================\n");
        appendOutput("  MEMBER MENU - Choose an action\n");
        appendOutput("========================================\n");
        appendOutput("1. Browse Available Books\n");
        appendOutput("2. Borrow a Book\n");
        appendOutput("3. View My Borrowed Books\n");
        appendOutput("4. View My Fine Amount\n");
        appendOutput("5. Pay Fine\n");
        appendOutput("0. Logout\n");
        appendOutput("========================================\n");
        appendOutput("Enter your choice (0-5):\n");
    }

    /**
     * Handle librarian actions
     */
    private void handleLibrarianAction(int choice) {
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                viewAllBooks();
                break;
            case 3:
                searchBook();
                break;
            case 4:
                issueBook();
                break;
            case 5:
                returnBook();
                break;
            case 6:
                viewMembers();
                break;
            case 7:
                viewTransactions();
                break;
            case 0:
                logout();
                break;
            default:
                appendOutput("Invalid choice. Please enter 0-7.\n");
                showLibrarianMenu();
        }
    }

    /**
     * Handle member actions
     */
    private void handleMemberAction(int choice) {
        switch (choice) {
            case 1:
                browseBooks();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                viewMyBooks();
                break;
            case 4:
                viewMyFine();
                break;
            case 5:
                payFine();
                break;
            case 0:
                logout();
                break;
            default:
                appendOutput("Invalid choice. Please enter 0-5.\n");
                showMemberMenu();
        }
    }

    // Librarian Actions
    private void addBook() {
        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();

        Object[] fields = {
                "Book ID:", idField,
                "Title:", titleField,
                "Author:", authorField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Add New Book",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int bookId = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String author = authorField.getText();

                if (!title.isEmpty() && !author.isEmpty()) {
                    Book book = new Book(bookId, title, author, 2024);
                    library.addBook(book);
                    appendAndShowLibrarianMenu("\n✓ Book added successfully!\n");
                } else {
                    appendAndShowLibrarianMenu("\n✗ Please fill all fields.\n");
                }
            } catch (NumberFormatException e) {
                appendAndShowLibrarianMenu("\n✗ Invalid Book ID.\n");
            }
        }
    }

    private void viewAllBooks() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== All Books in Library ===\n");
        sb.append("================================================\n");
        
        // Get books from library (assuming getBooks() method exists)
        // If getBooks() doesn't exist, this will need adjustment
        sb.append("Total Books: ").append(library.getTotalBooks()).append("\n");
        sb.append("Available: ").append(library.getAvailableBooks().size()).append("\n");
        sb.append("Borrowed: ").append(library.getBorrowedBooks().size()).append("\n");
        sb.append("================================================\n");
        appendAndShowLibrarianMenu(sb.toString());
    }

    private void searchBook() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Book ID:");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int bookId = Integer.parseInt(idStr);
                Book book = library.findBookById(bookId);
                
                if (book != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("\n=== Book Found ===\n");
                    sb.append("ID: ").append(book.getBookId()).append("\n");
                    sb.append("Title: ").append(book.getTitle()).append("\n");
                    sb.append("Author: ").append(book.getAuthor()).append("\n");
                    sb.append("Status: ").append(book.isBorrowed() ? "Borrowed" : "Available").append("\n");
                    sb.append("==================\n");
                    appendAndShowLibrarianMenu(sb.toString());
                } else {
                    appendAndShowLibrarianMenu("\n✗ Book with ID " + bookId + " not found.\n");
                }
            } catch (NumberFormatException e) {
                appendAndShowLibrarianMenu("\n✗ Invalid Book ID.\n");
            }
        }
    }

    private void issueBook() {
        JTextField memIdField = new JTextField();
        JTextField bookIdField = new JTextField();

        Object[] fields = {
                "Member ID:", memIdField,
                "Book ID:", bookIdField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Issue Book to Member",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int memberId = Integer.parseInt(memIdField.getText());
                int bookId = Integer.parseInt(bookIdField.getText());

                Member member = library.findMemberById(memberId);
                Book book = library.findBookById(bookId);

                if (member != null && book != null && !book.isBorrowed()) {
                    library.issueBook(member, book);
                    appendAndShowLibrarianMenu("\n✓ Book \"" + book.getTitle() + "\" issued to " + member.getName() + "!\n");
                } else {
                    appendAndShowLibrarianMenu("\n✗ Error: Member not found, book not found, or book already borrowed.\n");
                }
            } catch (NumberFormatException e) {
                appendAndShowLibrarianMenu("\n✗ Invalid input format.\n");
            }
        }
    }

    private void returnBook() {
        JTextField memIdField = new JTextField();
        JTextField bookIdField = new JTextField();
        JTextField daysLateField = new JTextField("0");

        Object[] fields = {
                "Member ID:", memIdField,
                "Book ID:", bookIdField,
                "Days Late:", daysLateField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Accept Book Return",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int memberId = Integer.parseInt(memIdField.getText());
                int bookId = Integer.parseInt(bookIdField.getText());
                int daysLate = Integer.parseInt(daysLateField.getText());

                Member member = library.findMemberById(memberId);
                Book book = library.findBookById(bookId);

                if (member != null && book != null && book.isBorrowed()) {
                    library.returnBook(member, book, daysLate);
                    appendAndShowLibrarianMenu("\n✓ Book \"" + book.getTitle() + "\" returned by " + member.getName() + ". Days late: " + daysLate + "\n");
                } else {
                    appendAndShowLibrarianMenu("\n✗ Error: Member not found, book not found, or book not borrowed.\n");
                }
            } catch (NumberFormatException e) {
                appendAndShowLibrarianMenu("\n✗ Invalid input format.\n");
            }
        }
    }

    private void viewMembers() {
        StringBuilder sb = new StringBuilder("\n=== All Members ===\n");
        for (Member member : allMembers) {
            sb.append("ID: ").append(member.getUserId()).append(", Name: ").append(member.getName()).append("\n");
        }
        appendAndShowLibrarianMenu(sb.toString());
    }

    private void viewTransactions() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== Transaction History ===\n");
        sb.append("================================================\n");
        sb.append("Total Transactions: ").append(library.getTotalTransactions()).append("\n");
        sb.append("================================================\n");
        appendAndShowLibrarianMenu(sb.toString());
    }

    // Member Actions
    private void browseBooks() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== Available Books for Borrowing ===\n");
        sb.append("================================================\n");
        
        ArrayList<Book> availableBooks = library.getAvailableBooks();
        if (availableBooks.isEmpty()) {
            sb.append("No books available at this moment.\n");
        } else {
            for (Book book : availableBooks) {
                sb.append("ID: ").append(book.getBookId()).append(" | ");
                sb.append("Title: ").append(book.getTitle()).append(" | ");
                sb.append("Author: ").append(book.getAuthor()).append("\n");
            }
        }
        sb.append("================================================\n");
        appendAndShowMemberMenu(sb.toString());
    }

    private void borrowBook() {
        String bookId = JOptionPane.showInputDialog(this, "Enter Book ID to borrow:");
        if (bookId != null && !bookId.isEmpty()) {
            try {
                int id = Integer.parseInt(bookId);
                Book book = library.findBookById(id);
                Member member = (Member) currentUser;
                
                if (book != null && !book.isBorrowed()) {
                    if (member.borrowBook(book)) {
                        appendAndShowMemberMenu("\n✓ Successfully borrowed \"" + book.getTitle() + "\"!\n");
                    } else {
                        appendAndShowMemberMenu("\n✗ Could not borrow book at this time.\n");
                    }
                } else if (book == null) {
                    appendAndShowMemberMenu("\n✗ Book with ID " + id + " not found.\n");
                } else {
                    appendAndShowMemberMenu("\n✗ Book is already borrowed by someone else.\n");
                }
            } catch (NumberFormatException e) {
                appendAndShowMemberMenu("\n✗ Invalid Book ID.\n");
            }
        } else {
            showMemberMenu();
        }
    }

    private void viewMyBooks() {
        StringBuilder sb = new StringBuilder("\n=== My Borrowed Books ===\n");
        Member member = (Member) currentUser;
        if (member.getBorrowedBooks().isEmpty()) {
            sb.append("No books borrowed.\n");
        } else {
            for (Book book : member.getBorrowedBooks()) {
                sb.append("- ").append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
            }
        }
        appendAndShowMemberMenu(sb.toString());
    }

    private void viewMyFine() {
        Member member = (Member) currentUser;
        appendAndShowMemberMenu("\nYour fine amount: $" + member.getFineAmount() + "\n");
    }

    private void payFine() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to pay:");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                Member member = (Member) currentUser;
                member.payFine(amount);
                appendAndShowMemberMenu("\n✓ Paid $" + amount + ". Remaining fine: $" + member.getFineAmount() + "\n");
            } catch (NumberFormatException e) {
                appendAndShowMemberMenu("\n✗ Invalid amount.\n");
            }
        } else {
            showMemberMenu();
        }
    }

    /**
     * Logout
     */
    private void logout() {
        currentUser = null;
        appendOutput("Logged out successfully.\n");
        showWelcome();
    }

    /**
     * Append text to output area
     */
    private void appendOutput(String text) {
        outputArea.append(text);
    }

    /**
     * Main method to run the GUI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI());
    }
}
