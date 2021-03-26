package ru.otus.library.sevices.library;

public interface LibraryService {
    void addAuthor(String firstName, String lastName, String middleName);

    void addGenre(String genreName);

    void addBook(String title, long authorId, long genreId);

    void updateBook(long bookId, long authorId, long genreId, String title);

    void deleteBook(long id);

    void readBookById(long id);

    void readBookByTitle(String title);

    void readAllAuthor();

    void readAllGenre();

    void readAllBooks();
}