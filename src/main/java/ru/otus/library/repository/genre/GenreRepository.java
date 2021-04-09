package ru.otus.library.repository.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre save(Genre genre);

    Genre findById(long id);

    List<Genre> findAll();

    void deleteById(long id);
}
