package ru.otus.library.sevices.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;
import ru.otus.library.repository.book.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public void addBook(String title, Author author, Genre genre) {
        Book book = new Book(0, title, author, genre, null);
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }
}
