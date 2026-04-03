package src.service;
import java.util.ArrayList;
import java.util.stream.Collectors;

import src.model.Book;
import src.model.Member;

import java.util.Comparator;

/**
 * LibraryUtils class - demonstrates Lambda usage for advanced operations
 * Shows Stream API, functional interfaces, and lambda expressions
 */
public class LibraryUtils {

    /**
     * Search books using lambda with predicate
     * Example: Filter books by title containing keyword
     */
    public static ArrayList<Book> searchByKeyword(ArrayList<Book> books, String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get available books using lambda filter
     */
    public static ArrayList<Book> getAvailableBooks(ArrayList<Book> books) {
        return books.stream()
                .filter(book -> !book.isBorrowed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get borrowed books using lambda filter
     */
    public static ArrayList<Book> getBorrowedBooks(ArrayList<Book> books) {
        return books.stream()
                .filter(Book::isBorrowed)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sort books by title using lambda comparator
     */
    public static ArrayList<Book> sortByTitle(ArrayList<Book> books) {
        return books.stream()
                .sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sort books by author using lambda comparator
     */
    public static ArrayList<Book> sortByAuthor(ArrayList<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sort books by year using lambda comparator
     */
    public static ArrayList<Book> sortByYear(ArrayList<Book> books) {
        return books.stream()
                .sorted((b1, b2) -> Integer.compare(b1.getPublicationYear(), b2.getPublicationYear()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get books by author using lambda filter
     */
    public static ArrayList<Book> getBooksByAuthor(ArrayList<Book> books, String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get books published in a specific year
     */
    public static ArrayList<Book> getBooksByYear(ArrayList<Book> books, int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Count books by borrowing status using lambda
     */
    public static long countAvailableBooks(ArrayList<Book> books) {
        return books.stream()
                .filter(book -> !book.isBorrowed())
                .count();
    }

    /**
     * Get members with fines using lambda filter
     */
    public static ArrayList<Member> getMembersWithFines(ArrayList<Member> members) {
        return members.stream()
                .filter(member -> member.getFineAmount() > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sort members by fine amount using lambda
     */
    public static ArrayList<Member> sortMembersByFine(ArrayList<Member> members) {
        return members.stream()
                .sorted((m1, m2) -> Double.compare(m2.getFineAmount(), m1.getFineAmount()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Calculate total fines using lambda reduce
     */
    public static double calculateTotalFines(ArrayList<Member> members) {
        return members.stream()
                .map(Member::getFineAmount)
                .reduce(0.0, Double::sum);
    }

    /**
     * Print books using lambda forEach
     */
    public static void printBooks(ArrayList<Book> books) {
        books.forEach(book -> System.out.println("     " + book));
    }

    /**
     * Print members using lambda forEach
     */
    public static void printMembers(ArrayList<Member> members) {
        members.forEach(member -> System.out.println("     " + member));
    }
}
