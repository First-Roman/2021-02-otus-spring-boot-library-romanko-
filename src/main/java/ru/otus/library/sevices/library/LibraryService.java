package ru.otus.library.sevices.library;

import ru.otus.library.dto.AuthorDTO;
import ru.otus.library.dto.BookDTO;
import ru.otus.library.dto.GenreDTO;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;

import java.util.List;

public interface LibraryService {
    List<Book> getAllBook();

    List<Genre> getAllGenre();

    List<Author> getAllAuthor();

    Book getBookById(long id);

    Author getAuthorById(long id);

    Genre getGenreById(long id);

    void addBook(BookDTO bookDTO);

    void addGenre(GenreDTO genreDTO);

    void addAuthor(AuthorDTO authorDTO);

    void updateBook(BookDTO bookDTO);

    void updateGenre(GenreDTO genreDTO);

    void updateAuthor(AuthorDTO authorDTO);

    void removeBookById(long id);

    void removeGenreById(long id);

    void removeAuthorById(long id);
}
