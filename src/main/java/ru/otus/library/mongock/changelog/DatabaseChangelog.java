package ru.otus.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;
import ru.otus.library.models.Genre;
import ru.otus.library.repository.author.AuthorRepository;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.comment.CommentRepository;
import ru.otus.library.repository.genre.GenreRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "FirstRoman", runAlways = true)
    public void dropDb(MongoDatabase mongoDatabase) {
        mongoDatabase.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "FirstRoman", runAlways = true)
    public void insertData(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository, CommentRepository commentRepository) {
        Author author = new Author();
        author.setId("1");
        author.setLastName("Толстой");
        author.setMiddleName("Николаевич");
        author.setFirstName("Лев");
        authorRepository.save(author);
        Genre genre = new Genre();
        genre.setId("1");
        genre.setGenreName("Классика");
        genreRepository.save(genre);
        Book book = new Book();
        book.setId("1");
        book.setGenre(genre);
        book.setAuthor(author);
        book.setTitle("Война и мир");
        bookRepository.save(book);
        Book bookTolstoy = new Book();
        bookTolstoy.setId("3");
        bookTolstoy.setGenre(genre);
        bookTolstoy.setAuthor(author);
        bookRepository.save(bookTolstoy);
        Comment comment = new Comment();
        comment.setNikName("FirstRoman");
        comment.setComment("Nice!");
        comment.setBookId(book.getId());
        commentRepository.save(comment);
        Author author1 = new Author();
        author1.setId("2");
        author1.setFirstName("Александр");
        author1.setLastName("Пушкин");
        author1.setMiddleName("Сергеевич");
        authorRepository.save(author1);
        Book bookPushkin = new Book();
        bookPushkin.setId("2");
        bookPushkin.setTitle("Евгений Онегин");
        bookPushkin.setAuthor(author1);
        bookPushkin.setGenre(genre);
        bookRepository.save(bookPushkin);

    }

}
