package pl.camp.it.library.dao;

import pl.camp.it.library.model.Book;
import pl.camp.it.library.model.User;

import java.util.List;

public interface IBookDAO {

    void addBook(Book book);
    List<Book> getAllBooks();
    List<Book> findBooks(String pattern);
    List<Book> getBooksByAuthorId(int id);
    List<Book> getBooksByCategory(Book.Category category); // typ enum, klasa w klasie
    Book getBookById(int id);
}
