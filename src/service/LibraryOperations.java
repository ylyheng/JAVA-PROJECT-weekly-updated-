package src.service;
import src.model.Book;

interface LibraryOperations {
    void addBook(Book b);
    Book findBookByTitle(String title);
}