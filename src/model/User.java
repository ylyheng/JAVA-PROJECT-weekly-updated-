package src.model;

/**
 * User abstract class - base class for all library users
 * Demonstrates inheritance and abstraction
 * All users must have userId and name
 * Subclasses must implement their specific role
 */
public abstract class User {
    // Protected fields - accessible by subclasses
    protected int userId;
    protected String name;
    protected String email;

    // Constructor
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Abstract method - each user type must provide their role
     * Demonstrates polymorphism
     */
    public abstract String getRole();

    /**
     * Abstract method - each user type has specific actions
     * Demonstrates polymorphism
     */
    public abstract void displayMenu();

    @Override
    public String toString() {
        return getRole() + "{id=" + userId + ", name='" + name + "', email='" + email + "'}";
    }
}
