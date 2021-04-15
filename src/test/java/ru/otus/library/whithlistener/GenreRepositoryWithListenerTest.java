package ru.otus.library.whithlistener;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ru.otus.library.AbstractMongoRepositoryTest;
import ru.otus.library.events.MongoGenreCascadeDeleteEventListener;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.genre.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестируем правильное поведение лисенеров GenreRepository")
@Import(MongoGenreCascadeDeleteEventListener.class)
public class GenreRepositoryWithListenerTest extends AbstractMongoRepositoryTest {

    private static final int EXPECTED_SIZE_BEFORE_DELETE = 3;
    private static final int EXPECTED_SIZE_AFTER_DELETE = 0;
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    private final static String ID = "1";

    @Test
    @DisplayName("Корректное удаление книг, когда удалили жанр")
    public void whenGenreDeleteCascadeDeleteBook() {
        Genre genre = genreRepository.findById(ID).orElseThrow();
        List<Book> booksBeforeDelete = bookRepository.findAllBooksByGenre(ID);
        assertThat(booksBeforeDelete.size()).isEqualTo(EXPECTED_SIZE_BEFORE_DELETE);
        genreRepository.delete(genre);
        List<Book> booksAfterDelete = bookRepository.findAllBooksByGenre(ID);
        assertThat(booksAfterDelete.size()).isEqualTo(EXPECTED_SIZE_AFTER_DELETE);
    }

}
