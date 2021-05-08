package ru.otus.library.convertor.genre;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.GenreDTO;
import ru.otus.library.models.Genre;

@Component
public class ConverterGenreToGenreDTO implements Converter<Genre, GenreDTO> {
    @Override
    public GenreDTO convert(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setGenre(genre.getGenreName());
        genreDTO.setId(genre.getId());
        genreDTO.setText(genre.getGenreName());
        return genreDTO;
    }
}
