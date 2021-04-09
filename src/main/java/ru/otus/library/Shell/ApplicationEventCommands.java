package ru.otus.library.Shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.sevices.library.LibraryService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventCommands {
    private final LibraryService libraryService;

    @ShellMethod(value = "Add new author in format (FirstName LastName MiddleName)", key = {"aa", "add_author", "addAuthor"})
    public void addAuthor(@ShellOption String firstName, @ShellOption String lastName, @ShellOption String middleName) {
        libraryService.addAuthor(firstName, lastName, middleName);
    }

    @ShellMethod(value = "Add new genre format (GenreName)", key = {"ag", "add_genre", "addGenre"})
    void addGenre(@ShellOption String genreName) {
        libraryService.addGenre(genreName);
    }

    @ShellMethod(value = "Add new book in format (Title AuthorId GenreId)", key = {"ab", "add_book", "addBook"})
    void addBook(@ShellOption String title, @ShellOption long authorId, @ShellOption long genreId) {
        libraryService.addBook(title, authorId, genreId);
    }

    @ShellMethod(value = "Read all author", key = {"raa", "read_all_author"})
    public void readAllAuthor() {
        libraryService.readAllAuthor();
    }

    @ShellMethod(value = "Read all genre", key = {"rag", "read_all_genre"})
    public void readAllGenre() {
        libraryService.readAllGenre();
    }

    @ShellMethod(value = "Read all books", key = {"rab", "read_all_books"})
    public void readAllBooks() {
        libraryService.readAllBooks();
    }


    @ShellMethod(value = "Read book by id", key = {"rbi", "read_book_by_id"})
    public void readBookById(@ShellOption long id) {
        libraryService.readBookById(id);
    }

    @ShellMethod(value = "Read book by title", key = {"rbt", "read_book_by_title"})
    public void readBookByTitle(@ShellOption String title) {
        libraryService.readBookByTitle(title);
    }

    @ShellMethod(value = "Read comment for book by id", key = {"rcb", "read_comment_book_id"})
    public void readCommentByBookId(@ShellOption long bookId) {
        libraryService.readCommentByBookId(bookId);
    }

    @ShellMethod(value = "Add comment to book in format ('comment' 'you nik name' 'book id')", key = {"acb", "add_comment_book"})
    public void addComment(@ShellOption String comment, @ShellOption String nikName, @ShellOption long bookId) {
        libraryService.addComment(comment, nikName, bookId);
    }

    @ShellMethod(value = "Read comment by id", key = {"rci", "read_comment_by_id"})
    public void readCommentById(@ShellOption long id) {
        libraryService.readCommentById(id);
    }

    @ShellMethod(value = "Delete all comment about book by id", key = {"dcb", "delete_comment_by_book_id"})
    public void deleteCommentByBookId(@ShellOption long bookId) {
        libraryService.deleteAllCommentByBookId(bookId);
    }

    @ShellMethod(value = "Delete book by id", key = {"dbi", "delete_book_by_id"})
    public void deleteBookById(@ShellOption long id) {
        libraryService.deleteBook(id);
    }

    @ShellMethod(value = "Update book in format ('New Title' bookId authorId genreId)", key = {"ubi", "update_book_by_id"})
    public void updateBookById(@ShellOption String newTitle, @ShellOption long bookId, @ShellOption long authorId, @ShellOption long genreId) {
        libraryService.updateBook(bookId, authorId, genreId, newTitle);
    }

}
