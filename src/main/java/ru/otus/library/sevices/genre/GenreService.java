package ru.otus.library.sevices.genre;

import ru.otus.library.models.Genre;

import java.util.List;

public interface GenreService {
    void addGenre(String genreName);

    Genre getGenreById(long id);

    List<Genre> getAllGenre();

    void deleteGenreById(long id);
}
