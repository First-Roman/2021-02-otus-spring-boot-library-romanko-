package ru.otus.library.sevices.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.book.BookDAO;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceShell implements BookService {

    private final BookDAO bookDAO;

    @Override
    public void addBook(String title, Author author, Genre genre) {
        long id = bookDAO.count() + 1;
        Book book = new Book(id, title, author, genre);
        bookDAO.insert(book);
    }

    @Override
    public Book getBookById(long id) {
        return bookDAO.getById(id);
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookDAO.getBookByTitle(title);
    }

    @Override
    public List<Book> getAllBook() {
        return bookDAO.getAll();
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.update(book);
    }


    @Override
    public void deleteBookById(long id) {
        bookDAO.delete(id);
    }
}
