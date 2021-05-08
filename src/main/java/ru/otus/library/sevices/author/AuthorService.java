package ru.otus.library.sevices.author;

import ru.otus.library.dto.AuthorDTO;
import ru.otus.library.models.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(String firstName, String lastName, String middleName);

    void updateAuthor(Author author);

    Author getAuthorById(long id);

    List<Author> getAllAuthor();

    void deleteAuthorById(long id);
}
