package ru.otus.library.repository.book;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;
import ru.otus.library.models.Genre;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестируем репозиторий книг")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    public static final int EXCEPTED_ID_CLASSIC = 1;
    public static final String EXCEPTED_GENRE_NAME_CLASSIC = "Классика";
    public static final int ACTUAL_AUTHOR_ID = 1;
    public static final String EXCEPTED_FIRST_NAME = "Лев";
    public static final String EXCEPTED_LAST_NAME = "Толстой";
    public static final String EXCEPTED_MIDDLE_NAME = "Николаевич";
    public static final String EXCEPTED_NEW_TITLE = "Анна каренина";
    public static final int ACTUAL_ID = 1;
    public static final String EXCEPTED_TITLE_WIN = "Война и мир";
    public static final int EXCEPTED_SIZE = 3;
    public static final int EXPECTED_COUNT_QUERY = 1;

    @Autowired
    BookRepositoryJpa bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должен корректно найти по id")
    void findByIdTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        List<Comment> comments = new ArrayList<>();
        Book exceptedBook = new Book(ACTUAL_ID, EXCEPTED_TITLE_WIN, exceptedAuthor, exceptedGenre, comments);
        Book actualBook = bookRepository.findById(exceptedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(exceptedBook);
    }

    @Test
    @DisplayName("должен корректно сохранить")
    void saveTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        List<Comment> comments = new ArrayList<>();
        Book exceptedBook = new Book(0, EXCEPTED_NEW_TITLE, exceptedAuthor, exceptedGenre, comments);
        bookRepository.save(exceptedBook);
        Book actualBook = bookRepository.findById(exceptedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(exceptedBook);
    }

    @Test
    @DisplayName("должен корректно обновлять")
    void updateTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        List<Comment> comments = new ArrayList<>();
        Book exceptedBook = new Book(ACTUAL_ID, EXCEPTED_NEW_TITLE, exceptedAuthor, exceptedGenre, comments);
        bookRepository.save(exceptedBook);
        Book actualBook = bookRepository.findById(exceptedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(exceptedBook);
    }

    @Test
    @DisplayName("должен корректно отдать все записи")
    void findAllTest() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(EXCEPTED_SIZE)
                .allMatch(book -> book.getComments() != null)
                .allMatch(book -> book.getAuthor() != null)
                .allMatch(book -> book.getGenre() != null);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_COUNT_QUERY);
    }

    @Test
    @DisplayName("должен корректно отдать все записи по наименованию")
    void findByTitleTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        List<Comment> comments = new ArrayList<>();
        Book exceptedBook = new Book(ACTUAL_ID, EXCEPTED_TITLE_WIN, exceptedAuthor, exceptedGenre, comments);
        List<Book> actualBooks = bookRepository.findByTitle(EXCEPTED_TITLE_WIN);
        assertThat(actualBooks).usingFieldByFieldElementComparator().containsAnyOf(exceptedBook);
    }

    @Test
    @DisplayName("должен корректно удалить запись")
    void deleteByIdTest() {
        assertThatCode(() -> bookRepository.findById(ACTUAL_ID)).doesNotThrowAnyException();
        bookRepository.deleteById(ACTUAL_ID);
        assertThatThrownBy(() -> Optional.ofNullable(bookRepository.findById(ACTUAL_ID)).orElseThrow()).isInstanceOf(NoSuchElementException.class);
    }
}