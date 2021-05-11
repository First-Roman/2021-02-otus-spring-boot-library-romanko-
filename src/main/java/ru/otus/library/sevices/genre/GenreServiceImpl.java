package ru.otus.library.sevices.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.models.Genre;
import ru.otus.library.repository.genre.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public void addGenre(String genreName) {
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        genreRepository.save(genre);
    }

    @Override
    public Genre getGenreById(String id) {
        return genreRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteGenreById(String id) {
        genreRepository.deleteById(id);
    }
}
