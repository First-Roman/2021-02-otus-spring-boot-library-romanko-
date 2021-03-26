package ru.otus.library.sevices.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.genre.GenreDAO;
import ru.otus.library.domain.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceShell implements GenreService {

    private final GenreDAO genreDAO;

    @Override
    public void addGenre(String genreName) {
        long id = genreDAO.count() + 1;
        Genre genre = new Genre(id, genreName);
        genreDAO.insert(genre);
    }

    @Override
    public Genre getGenreById(long id) {
        return genreDAO.getById(id);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreDAO.getAll();
    }
}
