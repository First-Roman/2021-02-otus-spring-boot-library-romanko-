package ru.otus.library.sevices.library;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.sevices.author.AuthorService;
import ru.otus.library.sevices.book.BookService;
import ru.otus.library.sevices.genre.GenreService;

import java.util.List;

@Service
@AllArgsConstructor
public class LibraryServiceShell implements LibraryService {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    @Override
    public void addAuthor(String firstName, String lastName, String middleName) {
        authorService.addAuthor(firstName, lastName, middleName);
        System.out.println("Author add, success!");
    }

    @Override
    public void addGenre(String genreName) {
        genreService.addGenre(genreName);
        System.out.println("Genre add, success!");
    }

    @Override
    public void addBook(String title, long authorId, long genreId) {
        Author author = null;
        Genre genre = null;
        try {
            author = authorService.getAuthorById(authorId);
        } catch (Exception e) {
            System.out.println("We don't have an author with this id!");
        }
        try {
            genre = genreService.getGenreById(genreId);
        } catch (Exception e) {
            System.out.println("We don't have an genre with this id!");
        }
        if (author != null && genre != null) {
            bookService.addBook(title, author, genre);
            System.out.println("Book add, success!");
        } else {
            System.out.println("Book add, fail!");
        }
    }

    @Override
    public void updateBook(long bookId, long authorId, long genreId, String title) {
        Author author = null;
        Genre genre = null;
        Book book = null;
        try {
            author = authorService.getAuthorById(authorId);
        } catch (Exception e) {
            System.out.println("We don't have an author with this id!");
        }
        try {
            genre = genreService.getGenreById(genreId);
        } catch (Exception e) {
            System.out.println("We don't have an genre with this id!");
        }
        try {
            book = bookService.getBookById(bookId);
        } catch (Exception e) {
            System.out.println("We don't have an book with this id!");
        }
        if (author != null && genre != null && book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            bookService.updateBook(book);
            System.out.println("Book update, success!");
        } else {
            System.out.println("Book update, fail!");
        }
    }


    @Override
    public void deleteBook(long id) {
        bookService.deleteBookById(id);
    }

    @Override
    public void readBookById(long id) {
        try {
            Book book = bookService.getBookById(id);
            printBook(book);
        } catch (Exception e) {
            System.out.println("We don't have any books with that id");
        }

    }

    @Override
    public void readBookByTitle(String title) {
        List<Book> books = bookService.getBookByTitle(title);
        if (books.size() > 0) {
            books.stream().forEach(book -> {
                printBook(book);
            });
        } else {
            System.out.println("We don't have any books with that title");
        }

    }

    @Override
    public void readAllAuthor() {
        List<Author> authors = authorService.getAllAuthor();
        if (authors.size() > 0) {
            authors.stream().forEach(author -> {
                System.out.println("Author id: " + author.getId() + ". Full name: " + author.getLastName() + " " + author.getFirstName() + " " + author.getMiddleName());
            });
        } else {
            System.out.println("We don't have any authors");
        }
    }

    @Override
    public void readAllGenre() {
        List<Genre> genres = genreService.getAllGenre();
        if (genres.size() > 0) {
            genres.stream().forEach(genre -> {
                System.out.println("Genre id: " + genre.getId() + ". Genre name: " + genre.getGenreName());
            });
        } else {
            System.out.println("We don't have any authors");
        }
    }

    @Override
    public void readAllBooks() {
        List<Book> books = bookService.getAllBook();
        if (books.size() > 0) {
            books.stream().forEach(book -> {
                printBook(book);
            });
        } else {
            System.out.println("We don't have any books in library");
        }
    }

    private void printBook(Book book) {
        String shotName = book.getAuthor().getLastName() + " " + book.getAuthor().getFirstName().substring(0, 1).toUpperCase() + "." + book.getAuthor().getMiddleName().substring(0, 1).toUpperCase();
        System.out.printf("Thea book id: %d titled: %s. Author: %s. Genre %s \n", book.getId(), book.getTitle(), shotName, book.getGenre().getGenreName());
    }
}
