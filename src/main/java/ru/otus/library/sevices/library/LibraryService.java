package ru.otus.library.sevices.library;

public interface LibraryService {
    void addAuthor(String firstName, String lastName, String middleName);

    void addGenre(String genreName);

    void addBook(String title, String authorId, String genreId);

    void updateBook(String bookId, String authorId, String genreId, String title);

    void deleteBook(String id);

    void readBookById(String id);

    void readBookByTitle(String title);

    void readAllAuthor();

    void readAllGenre();

    void readAllBooks();

    void readCommentByBookId(String bookId);

    void readCommentById(String id);

    void addComment(String comment, String nikName, String bookId);

    void deleteCommentById(String id);

    void deleteAllCommentByBookId(String bookId);
}
