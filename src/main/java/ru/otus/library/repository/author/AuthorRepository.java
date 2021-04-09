package ru.otus.library.repository.author;

import ru.otus.library.models.Author;

import java.util.List;

public interface AuthorRepository {
    Author save(Author author);

    Author findById(long id);

    List<Author> findAll();

    void deleteById(long id);
}
