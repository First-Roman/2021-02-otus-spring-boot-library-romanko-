package ru.otus.library.sevices.book;

import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;

import java.util.List;

public interface BookService {
    void addBook(String title, Author author, Genre genre);

    Book getBookById(long id);

    List<Book> getBookByTitle(String title);

    List<Book> getAllBook();

    void updateBook(Book book);

    void deleteBookById(long id);
}
