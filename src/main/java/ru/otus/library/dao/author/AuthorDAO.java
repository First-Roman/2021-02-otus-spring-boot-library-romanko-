package ru.otus.library.dao.author;

import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorDAO {
    int count();

    void insert(Author author);

    Author getById(long id);

    List<Author> getAll();

}
