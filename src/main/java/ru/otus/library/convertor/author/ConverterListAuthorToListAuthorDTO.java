package ru.otus.library.convertor.author;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.AuthorDTO;
import ru.otus.library.models.Author;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConverterListAuthorToListAuthorDTO implements Converter<List<Author>, List<AuthorDTO>> {
    private final ConverterAuthorToAuthorDTO converterAuthorToAuthorDTO;

    @Override
    public List<AuthorDTO> convert(List<Author> authors) {
        return authors.stream().map(author -> converterAuthorToAuthorDTO.convert(author)).collect(Collectors.toList());
    }
}
