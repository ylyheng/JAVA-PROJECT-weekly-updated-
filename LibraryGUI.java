import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * LibraryGUI - Graphical User Interface for Library Management System
 * Uses Swing for a user-friendly desktop application
 * Demonstrates GUI development with event handling and layout management
 */
public class LibraryGUI extends JFrame {
    private Library library;
    private User currentUser;
    private ArrayList<Librarian> librarians;
    private ArrayList<Member> allMembers;

    // GUI Components
    private JPanel mainPanel;
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton actionButton;
    private JLabel userLabel;
    private JLabel statusLabel;

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
    }

    /**
     * Setup the GUI components
     */
    private void setupGUI() {
        setTitle("Library Management System - GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Main Panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Top Panel - Title and Status
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("📚 Library Management System - OOP Demo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        userLabel = new JLabel("Status: Not Logged In");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(userLabel, BorderLayout.EAST);

        // Output Area
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        outputArea.setBackground(new Color(240, 240, 240));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputField = new JTextField(30);
        actionButton = new JButton("Submit");
        inputPanel.add(new JLabel("Input:"), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(actionButton, BorderLayout.EAST);

        // Status Label
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 11));

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register New Member");
        JButton clearButton = new JButton("Clear Output");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        // Add event listeners
        actionButton.addActionListener(e -> processAction());
        inputField.addActionListener(e -> processAction());
        loginButton.addActionListener(e -> showLoginMenu());
        registerButton.addActionListener(e -> registerMemberGUI());
        clearButton.addActionListener(e -> outputArea.setText(""));
        exitButton.addActionListener(e -> System.exit(0));

        // Add components to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

        add(mainPanel);

        // Initial display
        displayWelcomeMessage();
    }

    /**
     * Initialize system data
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
    private void displayWelcomeMessage() {
        outputArea.setText("\n╔════════════════════════════════════════╗\n" +
                "║  WELCOME TO LIBRARY MANAGEMENT SYSTEM  ║\n" +
                "║      OOP-Based Java Application        ║\n" +
                "║        With Swing GUI Interface        ║\n" +
                "╚════════════════════════════════════════╝\n\n" +
                "Click 'Login' button to get started or register as a new member.\n" +
                "Default Librarian ID: 1001, Password: pass1\n" +
                "Default Member ID: 2001, PIN: 1234\n");
    }

    /**
     * Show login menu
     */
    private void showLoginMenu() {
        String[] options = {"Librarian", "Member", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this,
                "Select User Type:", "Login",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            librarianLogin();
        } else if (choice == 1) {
            memberLogin();
        }
    }

    /**
     * Librarian login
     */
    private void librarianLogin() {
        JTextField libIdField = new JTextField("1001");
        JPasswordField passField = new JPasswordField("pass1");

        Object[] fields = {
                "Librarian ID:", libIdField,
                "Password:", passField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Librarian Login",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int librarianId = Integer.parseInt(libIdField.getText());
                String password = new String(passField.getPassword());

                if ((librarianId == 1001 && password.equals("pass1"))) {
                    for (Librarian lib : librarians) {
                        if (lib.getUserId() == librarianId) {
                            currentUser = lib;
                            updateUserStatus();
                            showLibrarianMenu();
                            return;
                        }
                    }
                }
                errorMessage("Invalid credentials!");
            } catch (NumberFormatException e) {
                errorMessage("Invalid Librarian ID!");
            }
        }
    }

    /**
     * Member login
     */
    private void memberLogin() {
        JTextField memIdField = new JTextField("2001");
        JPasswordField pinField = new JPasswordField("1234");

        Object[] fields = {
                "Member ID:", memIdField,
                "PIN:", pinField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Member Login",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int memberId = Integer.parseInt(memIdField.getText());
                String pin = new String(pinField.getPassword());

                if (pin.equals("1234")) {
                    for (Member member : allMembers) {
                        if (member.getUserId() == memberId) {
                            currentUser = member;
                            updateUserStatus();
                            showMemberMenu();
                            return;
                        }
                    }
                }
                errorMessage("Invalid Member ID or PIN!");
            } catch (NumberFormatException e) {
                errorMessage("Invalid Member ID!");
            }
        }
    }

    /**
     * Register new member
     */
    private void registerMemberGUI() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] fields = {
                "Name:", nameField,
                "Email:", emailField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Register New Member",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String email = emailField.getText();

            if (!name.isEmpty() && !email.isEmpty()) {
                int newMemberId = 2001 + allMembers.size();
                Member newMember = new Member(newMemberId, name, email, "2024-04-04");
                allMembers.add(newMember);
                library.addMember(newMember);

                successMessage("Member registered!\nID: " + newMemberId + "\nPIN: 1234");
            }
        }
    }

    /**
     * Show librarian menu
     */
    private void showLibrarianMenu() {
        outputArea.setText("\n╔════════════════════════════════════════╗\n" +
                "║       LIBRARIAN MENU                   ║\n" +
                "╠════════════════════════════════════════╣\n" +
                "║  1. Add New Book                       ║\n" +
                "║  2. View All Books                     ║\n" +
                "║  3. Search Book                        ║\n" +
                "║  4. Issue Book to Member              ║\n" +
                "║  5. Accept Book Return                ║\n" +
                "║  6. View All Members                  ║\n" +
                "║  7. View Transactions                 ║\n" +
                "║  8. Logout                            ║\n" +
                "╚════════════════════════════════════════╝\n" +
                "\nEnter your choice (1-8):\n");
        statusLabel.setText("Waiting for librarian action...");
    }

    /**
     * Show member menu
     */
    private void showMemberMenu() {
        outputArea.setText("\n╔════════════════════════════════════════╗\n" +
                "║         MEMBER MENU                    ║\n" +
                "╠════════════════════════════════════════╣\n" +
                "║  1. Browse Available Books            ║\n" +
                "║  2. Borrow a Book                     ║\n" +
                "║  3. View My Books                     ║\n" +
                "║  4. View Fine Amount                  ║\n" +
                "║  5. Pay Fine                          ║\n" +
                "║  6. Logout                            ║\n" +
                "╚════════════════════════════════════════╝\n" +
                "\nEnter your choice (1-6):\n");
        statusLabel.setText("Waiting for member action...");
    }

    /**
     * Process actions
     */
    private void processAction() {
        String input = inputField.getText().trim();
        inputField.setText("");

        if (currentUser == null) {
            errorMessage("Please login first!");
            return;
        }

        try {
            int choice = Integer.parseInt(input);

            if (currentUser instanceof Librarian) {
                processLibrarianAction(choice);
            } else if (currentUser instanceof Member) {
                processMemberAction(choice);
            }
        } catch (NumberFormatException e) {
            errorMessage("Please enter a valid number!");
        }
    }

    /**
     * Process librarian actions
     */
    private void processLibrarianAction(int choice) {
        switch (choice) {
            case 1:
                addBookGUI();
                break;
            case 2:
                viewAllBooksGUI();
                break;
            case 3:
                searchBookGUI();
                break;
            case 4:
                issueBookGUI();
                break;
            case 5:
                acceptReturnGUI();
                break;
            case 6:
                viewMembersGUI();
                break;
            case 7:
                viewTransactionsGUI();
                break;
            case 8:
                logout();
                break;
            default:
                errorMessage("Invalid choice! Please enter 1-8.");
        }
    }

    /**
     * Process member actions
     */
    private void processMemberAction(int choice) {
        switch (choice) {
            case 1:
                browseAvailableBooksGUI();
                break;
            case 2:
                borrowBookGUI();
                break;
            case 3:
                viewMyBooksGUI();
                break;
            case 4:
                viewMyFineGUI();
                break;
            case 5:
                payFineGUI();
                break;
            case 6:
                logout();
                break;
            default:
                errorMessage("Invalid choice! Please enter 1-6.");
        }
    }

    // Librarian Actions
    private void addBookGUI() {
        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();

        Object[] fields = {
                "Book ID:", idField,
                "Title:", titleField,
                "Author:", authorField,
                "Year:", yearField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Add New Book",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int bookId = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String author = authorField.getText();
                int year = Integer.parseInt(yearField.getText());

                Book book = new Book(bookId, title, author, year);
                library.addBook(book);
                successMessage("Book added successfully!");
                showLibrarianMenu();
            } catch (NumberFormatException e) {
                errorMessage("Invalid input format!");
            }
        }
    }

    private void viewAllBooksGUI() {
        StringBuilder sb = new StringBuilder("\n════════════════════════════════════════\n");
        sb.append("         ALL BOOKS IN LIBRARY\n");
        sb.append("════════════════════════════════════════\n");

        if (library.getTotalBooks() == 0) {
            sb.append("No books available.\n");
        } else {
            LibraryUtils.printBooks(new ArrayList<>(library.getBooks()));
        }

        sb.append("════════════════════════════════════════\n");
        sb.append("Total: ").append(library.getTotalBooks()).append(" | ");
        sb.append("Available: ").append(library.getAvailableBooks().size()).append(" | ");
        sb.append("Borrowed: ").append(library.getBorrowedBooks().size()).append("\n");

        outputArea.setText(sb.toString());
        showLibrarianMenu();
    }

    private void searchBookGUI() {
        String[] options = {"By Title", "By ID", "By Author"};
        int choice = JOptionPane.showOptionDialog(this,
                "Search by:", "Search Book",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            String title = JOptionPane.showInputDialog(this, "Enter title:");
            if (title != null) {
                Book book = library.findBookByTitle(title);
                if (book != null) {
                    successMessage("Book found:\n" + book);
                } else {
                    errorMessage("Book not found!");
                }
            }
        } else if (choice == 1) {
            String idStr = JOptionPane.showInputDialog(this, "Enter Book ID:");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    Book book = library.findBookById(id);
                    if (book != null) {
                        successMessage("Book found:\n" + book);
                    } else {
                        errorMessage("Book not found!");
                    }
                } catch (NumberFormatException e) {
                    errorMessage("Invalid ID!");
                }
            }
        } else if (choice == 2) {
            String author = JOptionPane.showInputDialog(this, "Enter author:");
            if (author != null) {
                Book book = library.findBookByAuthor(author);
                if (book != null) {
                    successMessage("Book found:\n" + book);
                } else {
                    errorMessage("Book not found!");
                }
            }
        }
        showLibrarianMenu();
    }

    private void issueBookGUI() {
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

                if (member != null && book != null) {
                    library.issueBook(member, book);
                    successMessage("Book issued successfully!");
                } else {
                    errorMessage("Member or Book not found!");
                }
            } catch (NumberFormatException e) {
                errorMessage("Invalid input!");
            }
        }
        showLibrarianMenu();
    }

    private void acceptReturnGUI() {
        JTextField memIdField = new JTextField();
        JTextField bookIdField = new JTextField();
        JTextField daysField = new JTextField("0");

        Object[] fields = {
                "Member ID:", memIdField,
                "Book ID:", bookIdField,
                "Days Late:", daysField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Accept Book Return",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int memberId = Integer.parseInt(memIdField.getText());
                int bookId = Integer.parseInt(bookIdField.getText());
                int daysLate = Integer.parseInt(daysField.getText());

                Member member = library.findMemberById(memberId);
                Book book = library.findBookById(bookId);

                if (member != null && book != null) {
                    library.returnBook(member, book, daysLate);
                    successMessage("Book returned successfully!");
                } else {
                    errorMessage("Member or Book not found!");
                }
            } catch (NumberFormatException e) {
                errorMessage("Invalid input!");
            }
        }
        showLibrarianMenu();
    }

    private void viewMembersGUI() {
        StringBuilder sb = new StringBuilder("\n════════════════════════════════════════\n");
        sb.append("      ALL REGISTERED MEMBERS\n");
        sb.append("════════════════════════════════════════\n");

        for (Member member : allMembers) {
            sb.append(member).append("\n");
        }

        sb.append("════════════════════════════════════════\n");
        sb.append("Total members: ").append(allMembers.size()).append("\n");

        outputArea.setText(sb.toString());
        showLibrarianMenu();
    }

    private void viewTransactionsGUI() {
        StringBuilder sb = new StringBuilder("\n════════════════════════════════════════\n");
        sb.append("       TRANSACTION HISTORY\n");
        sb.append("════════════════════════════════════════\n");

        if (library.getTotalTransactions() == 0) {
            sb.append("No transactions recorded.\n");
        }

        sb.append("════════════════════════════════════════\n");
        sb.append("Total transactions: ").append(library.getTotalTransactions()).append("\n");

        outputArea.setText(sb.toString());
        showLibrarianMenu();
    }

    // Member Actions
    private void browseAvailableBooksGUI() {
        StringBuilder sb = new StringBuilder("\n════════════════════════════════════════\n");
        sb.append("    AVAILABLE BOOKS FOR BORROWING\n");
        sb.append("════════════════════════════════════════\n");

        ArrayList<Book> available = library.getAvailableBooks();
        if (available.isEmpty()) {
            sb.append("No books available.\n");
        } else {
            available.forEach(book -> sb.append(book).append("\n"));
        }

        sb.append("════════════════════════════════════════\n");
        outputArea.setText(sb.toString());
        showMemberMenu();
    }

    private void borrowBookGUI() {
        String bookIdStr = JOptionPane.showInputDialog(this, "Enter Book ID:");
        if (bookIdStr != null) {
            try {
                int bookId = Integer.parseInt(bookIdStr);
                Book book = library.findBookById(bookId);
                Member member = (Member) currentUser;

                if (book != null && !book.isBorrowed()) {
                    if (member.borrowBook(book)) {
                        successMessage("Book borrowed successfully!");
                    }
                } else {
                    errorMessage("Book not found or already borrowed!");
                }
            } catch (NumberFormatException e) {
                errorMessage("Invalid Book ID!");
            }
        }
        showMemberMenu();
    }

    private void viewMyBooksGUI() {
        Member member = (Member) currentUser;
        StringBuilder sb = new StringBuilder("\n════════════════════════════════════════\n");
        sb.append("       YOUR BORROWED BOOKS\n");
        sb.append("════════════════════════════════════════\n");

        ArrayList<Book> borrowed = member.getBorrowedBooks();
        if (borrowed.isEmpty()) {
            sb.append("You have no borrowed books.\n");
        } else {
            borrowed.forEach(book -> sb.append(book).append("\n"));
        }

        sb.append("════════════════════════════════════════\n");
        outputArea.setText(sb.toString());
        showMemberMenu();
    }

    private void viewMyFineGUI() {
        Member member = (Member) currentUser;
        StringBuilder sb = new StringBuilder("\n════════════════════════════════════════\n");
        sb.append("          FINE INFORMATION\n");
        sb.append("════════════════════════════════════════\n");
        sb.append("Current Fine: $").append(String.format("%.2f", member.getFineAmount())).append("\n");
        if (member.getFineAmount() > 0) {
            sb.append("⚠ Please pay your fine.\n");
        } else {
            sb.append("✓ No pending fines!\n");
        }
        sb.append("════════════════════════════════════════\n");

        outputArea.setText(sb.toString());
        showMemberMenu();
    }

    private void payFineGUI() {
        Member member = (Member) currentUser;
        if (member.getFineAmount() <= 0) {
            errorMessage("No fines to pay!");
            showMemberMenu();
            return;
        }

        String amountStr = JOptionPane.showInputDialog(this, "Fine Amount: $" + 
                String.format("%.2f", member.getFineAmount()) + "\nEnter amount to pay:");
        if (amountStr != null) {
            try {
                double amount = Double.parseDouble(amountStr);
                if (member.payFine(amount)) {
                    successMessage("Payment successful!\nRemaining fine: $" + 
                            String.format("%.2f", member.getFineAmount()));
                } else {
                    errorMessage("Invalid amount!");
                }
            } catch (NumberFormatException e) {
                errorMessage("Invalid amount format!");
            }
        }
        showMemberMenu();
    }

    private void logout() {
        currentUser = null;
        updateUserStatus();
        displayWelcomeMessage();
    }

    private void updateUserStatus() {
        if (currentUser == null) {
            userLabel.setText("Status: Not Logged In");
        } else {
            userLabel.setText("Status: Logged in as " + currentUser.getName() + " (" + currentUser.getRole() + ")");
        }
    }

    private void successMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI frame = new LibraryGUI();
            frame.setVisible(true);
        });
    }
}
