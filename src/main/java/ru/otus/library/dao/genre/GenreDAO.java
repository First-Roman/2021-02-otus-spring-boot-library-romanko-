package ru.otus.library.dao.genre;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreDAO {
    int count();

    void insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();
}
