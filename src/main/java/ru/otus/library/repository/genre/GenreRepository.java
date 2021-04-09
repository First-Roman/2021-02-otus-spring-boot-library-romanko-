package ru.otus.library.repository.genre;

import ru.otus.library.models.Genre;

import java.util.List;

public interface GenreRepository {
    Genre save(Genre genre);

    Genre findById(long id);

    List<Genre> findAll();

    void deleteById(long id);
}
