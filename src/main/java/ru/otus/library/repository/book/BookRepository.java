package ru.otus.library.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

}
