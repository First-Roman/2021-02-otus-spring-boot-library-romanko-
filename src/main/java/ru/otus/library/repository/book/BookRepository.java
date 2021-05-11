package ru.otus.library.repository.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.library.models.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByTitle(String title);

    List<Book> findAllBookByAuthor(@Param("authorId") String authorId);

    List<Book> findAllBooksByGenre(@Param("genreId") String genreId);

    void deleteAllByAuthor(@Param("authorId") String authorId);

    void deleteAllByGenre(@Param("genreId") String genreId);
}
