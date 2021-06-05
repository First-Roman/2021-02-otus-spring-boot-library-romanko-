package ru.otus.library.repository.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.models.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
