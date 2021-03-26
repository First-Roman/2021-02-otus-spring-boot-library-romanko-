package ru.otus.library.dao.book;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Тестируем класс DAO Book")
@JdbcTest
@Import({BookDAOJdbc.class})
class BookDAOJdbcTest {

    public static final int EXCEPTED_ID_CLASSIC = 1;
    public static final String EXCEPTED_GENRE_NAME_CLASSIC = "Классика";
    public static final int ACTUAL_AUTHOR_ID = 1;
    public static final String EXCEPTED_FIRST_NAME = "Лев";
    public static final String EXCEPTED_LAST_NAME = "Толстой";
    public static final String EXCEPTED_MIDDLE_NAME = "Николаевич";
    public static final int EXPECTED_COUNT = 1;
    public static final int EXCEPTED_BOOK_ID = 2;
    public static final String EXCEPTED_TITLE = "Кавказский пленник";
    public static final String EXCEPTED_NEW_TITLE = "Анна каренина";
    public static final int ACTUAL_ID = 1;
    public static final String EXCEPTED_TITLE_WIN = "Война и мир";
    @Autowired
    BookDAOJdbc bookDAOJdbc;

    @DisplayName("Возвращает верное колличество")
    @Test
    void countTest() {
        int actualCount = bookDAOJdbc.count();
        assertThat(actualCount).isEqualTo(EXPECTED_COUNT);
    }

    @DisplayName("Создает новую книгу")
    @Test
    void insertTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        Book exceptedBook = new Book(EXCEPTED_BOOK_ID, EXCEPTED_TITLE, exceptedAuthor, exceptedGenre);
        bookDAOJdbc.insert(exceptedBook);
        Book actualBook = bookDAOJdbc.getById(exceptedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(exceptedBook);
    }

    @DisplayName("Возвращает книгу по id")
    @Test
    void getByIdTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        Book exceptedBook = new Book(ACTUAL_ID, EXCEPTED_TITLE_WIN, exceptedAuthor, exceptedGenre);
        Book actualBook = bookDAOJdbc.getById(ACTUAL_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(exceptedBook);
    }

    @DisplayName("Возвращает книги")
    @Test
    void getAllTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        Book exceptedBook = new Book(ACTUAL_ID, EXCEPTED_TITLE_WIN, exceptedAuthor, exceptedGenre);
        Book actualBook = bookDAOJdbc.getAll().get(0);
        AssertionsForClassTypes.assertThat(actualBook).usingRecursiveComparison().isEqualTo(exceptedBook);
    }

    @DisplayName("Возвращает книгу по наименованию")
    @Test
    void getBookByTitleTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        Book exceptedBook = new Book(ACTUAL_ID, EXCEPTED_TITLE_WIN, exceptedAuthor, exceptedGenre);
        Book actualBook = bookDAOJdbc.getBookByTitle(EXCEPTED_TITLE_WIN).get(0);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(exceptedBook);
    }

    @DisplayName("Обновляет книгу")
    @Test
    void updateTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        Book exceptedBook = new Book(ACTUAL_ID, EXCEPTED_NEW_TITLE, exceptedAuthor, exceptedGenre);
        bookDAOJdbc.update(exceptedBook);
        Book actualBook = bookDAOJdbc.getById(exceptedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(exceptedBook);
    }

    @DisplayName("Удаляет книгу")
    @Test
    void deleteTest() {
        assertThatCode(() -> bookDAOJdbc.getById(ACTUAL_ID)).doesNotThrowAnyException();
        bookDAOJdbc.delete(ACTUAL_ID);
        assertThatThrownBy(() -> bookDAOJdbc.getById(ACTUAL_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }


}