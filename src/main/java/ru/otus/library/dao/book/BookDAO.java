package ru.otus.library.dao.book;

import ru.otus.library.domain.Book;

import java.util.List;

public interface BookDAO {

    int count();

    void insert(Book book);

    void update(Book book);

    void delete(long id);

    Book getById(long id);

    List<Book> getAll();

    List<Book> getBookByTitle(String title);
}
