package ru.otus.library.convertor.author;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.AuthorDTO;
import ru.otus.library.models.Author;

@Component
public class ConverterAuthorToAuthorDTO implements Converter<Author, AuthorDTO> {
    @Override
    public AuthorDTO convert(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        String fullName = author.getLastName() + " " + author.getFirstName() + " " + author.getMiddleName();
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setMiddleName(author.getMiddleName());
        authorDTO.setId(author.getId());
        authorDTO.setText(fullName);
        return authorDTO;
    }
}
