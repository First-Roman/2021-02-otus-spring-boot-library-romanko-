package ru.otus.library.convertor.book;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.BookDTO;
import ru.otus.library.models.Book;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ConverterListBookToListBookDTO implements Converter<List<Book>, List<BookDTO>> {
    private final ConverterBookToBookDTO converterBookToBookDTO;

    @Override
    public List<BookDTO> convert(List<Book> books) {
        return books.stream().map(book -> {
            return converterBookToBookDTO.convert(book);
        }).collect(Collectors.toList());
    }
}
