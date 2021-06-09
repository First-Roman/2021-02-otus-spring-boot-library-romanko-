package ru.otus.library.sevices.library;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.AuthorDTO;
import ru.otus.library.dto.BookDTO;
import ru.otus.library.dto.GenreDTO;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;
import ru.otus.library.sevices.author.AuthorService;
import ru.otus.library.sevices.book.BookService;
import ru.otus.library.sevices.genre.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;


    @Override
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<Genre> getAllGenre() {
        return genreService.getAllGenre();
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<Author> getAllAuthor() {
        return authorService.getAllAuthor();
    }

    @Override
    public Book getBookById(long id) {
        return bookService.getBookById(id);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public Author getAuthorById(long id) {
        return authorService.getAuthorById(id);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public Genre getGenreById(long id) {
        return genreService.getGenreById(id);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void addBook(BookDTO bookDTO) {
        Author author = authorService.getAuthorById(bookDTO.getAuthorId());
        Genre genre = genreService.getGenreById(bookDTO.getGenreId());
        bookService.addBook(bookDTO.getTitle(), author, genre);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void addGenre(GenreDTO genreDTO) {
        genreService.addGenre(genreDTO.getGenre());
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void addAuthor(AuthorDTO authorDTO) {
        authorService.addAuthor(authorDTO.getFirstName(), authorDTO.getLastName(), authorDTO.getMiddleName());
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void updateBook(BookDTO bookDTO) {
        Author author = authorService.getAuthorById(bookDTO.getAuthorId());
        Genre genre = genreService.getGenreById(bookDTO.getGenreId());
        Book book = bookService.getBookById(bookDTO.getId());
        book.setAuthor(author);
        book.setGenre(genre);
        book.setTitle(bookDTO.getTitle());
        bookService.updateBook(book);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void updateGenre(GenreDTO genreDTO) {
        Genre genre = genreService.getGenreById(genreDTO.getId());
        genre.setGenreName(genreDTO.getGenre());
        genreService.updateGenre(genre);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void updateAuthor(AuthorDTO authorDTO) {
        Author author = authorService.getAuthorById(authorDTO.getId());
        author.setFirstName(authorDTO.getFirstName());
        author.setMiddleName(authorDTO.getMiddleName());
        author.setLastName(authorDTO.getLastName());
        authorService.updateAuthor(author);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void removeBookById(long id) {
        bookService.deleteBookById(id);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void removeGenreById(long id) {
        genreService.deleteGenreById(id);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void removeAuthorById(long id) {
        authorService.deleteAuthorById(id);
    }
}
