/**
 * Librarian class - represents a librarian
 * Inherits from User and demonstrates inheritance
 * Librarians manage library operations
 */
public class Librarian extends User {
    // Additional fields specific to librarians
    private String employeeId;
    private String department;
    private double salary;

    // Constructor
    public Librarian(int userId, String name, String email, String employeeId, String department, double salary) {
        super(userId, name, email); // Call parent constructor
        this.employeeId = employeeId;
        this.department = department;
        this.salary = salary;
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Polymorphic method - override from User
     */
    @Override
    public String getRole() {
        return "Librarian";
    }

    /**
     * Polymorphic method - override from User
     */
    @Override
    public void displayMenu() {
        System.out.println("\n=== Librarian Menu ===");
        System.out.println("1. Add New Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book by Title");
        System.out.println("4. Search Book by ID");
        System.out.println("5. Search Book by Author");
        System.out.println("6. Issue Book to Member");
        System.out.println("7. Accept Book Return");
        System.out.println("8. View All Members");
        System.out.println("9. View Transactions");
        System.out.println("10. Exit");
    }

    @Override
    public String toString() {
        return "Librarian{id=" + userId + ", name='" + name + "', email='" + email +
               "', employeeId='" + employeeId + "', department='" + department +
               "', salary=$" + String.format("%.2f", salary) + "}";
    }
}
