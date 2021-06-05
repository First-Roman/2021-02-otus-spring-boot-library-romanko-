package ru.otus.library.repository.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.models.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
