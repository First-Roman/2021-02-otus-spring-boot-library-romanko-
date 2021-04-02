package ru.otus.library.sevices.author;

import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(String firstName, String lastName, String middleName);

    Author getAuthorById(long id);

    List<Author> getAllAuthor();
}
