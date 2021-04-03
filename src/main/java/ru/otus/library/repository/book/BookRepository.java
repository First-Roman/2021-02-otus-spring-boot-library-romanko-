package ru.otus.library.repository.book;

import ru.otus.library.models.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);

    Book findById(long id);

    List<Book> findAll();

    List<Book> findByTitle(String title);

    void deleteById(long id);
}
