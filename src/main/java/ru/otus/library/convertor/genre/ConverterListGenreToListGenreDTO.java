package ru.otus.library.convertor.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.GenreDTO;
import ru.otus.library.models.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConverterListGenreToListGenreDTO implements Converter<List<Genre>, List<GenreDTO>> {
    private final ConverterGenreToGenreDTO converterGenreToGenreDTO;

    @Override
    public List<GenreDTO> convert(List<Genre> genres) {
        return genres.stream().map(genre -> {
            return converterGenreToGenreDTO.convert(genre);
        }).collect(Collectors.toList());
    }
}
