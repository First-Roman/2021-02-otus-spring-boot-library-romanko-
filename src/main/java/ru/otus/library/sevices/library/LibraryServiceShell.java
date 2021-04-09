package ru.otus.library.sevices.library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;
import ru.otus.library.models.Genre;
import ru.otus.library.sevices.author.AuthorService;
import ru.otus.library.sevices.book.BookService;
import ru.otus.library.sevices.comment.CommentService;
import ru.otus.library.sevices.genre.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceShell implements LibraryService {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final CommentService commentService;

    @Override
    @Transactional
    public void addAuthor(String firstName, String lastName, String middleName) {
        authorService.addAuthor(firstName, lastName, middleName);
        System.out.println("New author added!");
    }

    @Override
    @Transactional
    public void addGenre(String genreName) {
        genreService.addGenre(genreName);
        System.out.println("New genre added!");
    }

    @Override
    @Transactional
    public void addBook(String title, long authorId, long genreId) {
        Author author = authorService.getAuthorById(authorId);
        Genre genre = genreService.getGenreById(genreId);
        bookService.addBook(title, author, genre);
        System.out.println("New book added!");
    }

    @Override
    public void updateBook(long bookId, long authorId, long genreId, String title) {
        Author author = authorService.getAuthorById(authorId);
        Genre genre = genreService.getGenreById(genreId);
        Book book = bookService.getBookById(bookId);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setTitle(title);
        bookService.updateBook(book);
        System.out.println("The book is updated!");
    }

    @Override
    public void deleteBook(long id) {
        Book book = bookService.getBookById(id);
        bookService.deleteBookById(id);
        System.out.println("Book deleted!");
        printBook(book);
    }

    @Override
    @Transactional
    public void readBookById(long id) {
        Book book = bookService.getBookById(id);
        printBook(book);
    }

    @Override
    @Transactional
    public void readBookByTitle(String title) {
        List<Book> books = bookService.getBookByTitle(title);
        if (books.size() > 0) {
            books.forEach(book -> {
                printBook(book);
            });
        } else {
            System.out.println("We don't have any books with that title ;(");
        }
    }

    @Override
    public void readAllAuthor() {
        List<Author> authors = authorService.getAllAuthor();
        if (authors.size() > 0) {
            authors.forEach(author -> {
                String shotName = getShotName(author);
                System.out.printf("Author id: %d. Author name: %s \n", author.getId(), shotName);
            });
        }
    }

    @Override
    public void readAllGenre() {
        List<Genre> genres = genreService.getAllGenre();
        if (genres.size() > 0) {
            genres.forEach(genre -> {
                System.out.printf("Genre id: %d. Genre name: %s \n", genre.getId(), genre.getGenreName());
            });
        }
    }

    @Override
    public void readAllBooks() {
        List<Book> books = bookService.getAllBook();
        if (books.size() > 0) {
            books.forEach(book -> {
                printBook(book);
            });
        } else {
            System.out.println("We don't have any books ;(");
        }
    }

    @Override
    public void readCommentByBookId(long bookId) {
        List<Comment> comments = commentService.getAllCommentForBook(bookId);
        if (comments.size() > 0) {
            printComments(comments);
        } else {
            System.out.println("This book has no comments ;(");
        }
    }

    @Override
    public void readCommentById(long id) {
        Comment comment = commentService.getCommentById(id);
        printComment(comment);
    }

    @Override
    public void addComment(String comment, String nikName, long bookId) {
        commentService.addComment(comment, nikName, bookId);
        System.out.println("New comment add!");
    }

    @Override
    public void deleteCommentById(long id) {
        commentService.deleteCommentById(id);
        System.out.println("Comment deleted!");
    }

    @Override
    public void deleteAllCommentByBookId(long bookId) {
        commentService.deleteCommentByBookId(bookId);
        System.out.println("All comments on the book have been deleted!");
    }

    private void printBook(Book book) {
        String shotName = getShotName(book.getAuthor());
        System.out.printf("The book id: %d. Title:| %s. | Author: %s. Genre: %s. \n", book.getId(), book.getTitle(), shotName, book.getGenre().getGenreName());
        if (book.getComments().size() > 0) {
            printComments(book.getComments());
        }
    }

    private void printComments(List<Comment> comments) {
        comments.forEach(c -> {
            printComment(c);
        });
    }

    private void printComment(Comment c) {
        System.out.printf("Comment id: %d | %s. | Author comment: %s \n", c.getId(), c.getComment(), c.getNikName());
    }

    private String getShotName(Author author) {
        return author.getLastName() + " " + author.getFirstName().substring(0, 1).toUpperCase() + "." + author.getMiddleName().substring(0, 1).toUpperCase() + ".";
    }
}
