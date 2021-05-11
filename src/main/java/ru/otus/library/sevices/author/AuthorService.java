package ru.otus.library.sevices.author;

import ru.otus.library.models.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(String firstName, String lastName, String middleName);

    Author getAuthorById(String id);

    List<Author> getAllAuthor();

    void deleteAuthorById(String id);
}
