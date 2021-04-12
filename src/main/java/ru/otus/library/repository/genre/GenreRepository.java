package ru.otus.library.repository.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
