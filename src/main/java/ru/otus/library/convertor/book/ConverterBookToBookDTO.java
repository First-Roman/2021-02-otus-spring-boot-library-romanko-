package ru.otus.library.convertor.book;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.BookDTO;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;

@Component
public class ConverterBookToBookDTO implements Converter<Book, BookDTO> {
    @Override
    public BookDTO convert(Book book) {
        Author author = book.getAuthor();
        Genre genre = book.getGenre();
        String authorFullName = author.getLastName() + " " + author.getFirstName() + " " + author.getMiddleName();
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthorId(author.getId());
        bookDTO.setGenreId(genre.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(authorFullName);
        bookDTO.setGenre(genre.getGenreName());
        return bookDTO;
    }
}
