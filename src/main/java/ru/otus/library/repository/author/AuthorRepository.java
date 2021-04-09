package ru.otus.library.repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author save(Author author);

    Author findById(long id);

    List<Author> findAll();

    void deleteById(long id);
}
