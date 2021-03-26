package ru.otus.library.dao.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Author;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Тестирование DAO Author")
@JdbcTest
@Import(AuthorDAOJdbc.class)
class AuthorDAOJdbcTest {

    public static final int ACTUAL_AUTHOR_ID = 1;
    public static final String EXCEPTED_FIRST_NAME = "Лев";
    public static final String EXCEPTED_LAST_NAME = "Толстой";
    public static final String EXCEPTED_MIDDLE_NAME = "Николаевич";
    public static final int EXCEPTED_COUNT = 1;
    public static final int EXCEPTED_NEW_ID = 2;
    public static final String EXCEPTED_NEW_FIRST_NAME = "Александр";
    public static final String EXCEPTED_NEW_LAST_NAME = "Пушкин";
    public static final String EXCEPTED_NEW_MIDDLE_NAME = "Сергеевич";
    @Autowired
    AuthorDAOJdbc authorDAOJdbc;

    @DisplayName("Возвращает актуальное количество авторов")
    @Test
    void countTest() {
        int actualAuthor = authorDAOJdbc.count();
        assertThat(actualAuthor).isEqualTo(EXCEPTED_COUNT);
    }

    @DisplayName("Добавляет автора в бд")
    @Test
    void insertTest() {
        Author exceptedAuthor = new Author(EXCEPTED_NEW_ID, EXCEPTED_NEW_FIRST_NAME, EXCEPTED_NEW_LAST_NAME, EXCEPTED_NEW_MIDDLE_NAME);
        authorDAOJdbc.insert(exceptedAuthor);
        Author actualAuthor = authorDAOJdbc.getById(exceptedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(exceptedAuthor);
    }

    @DisplayName("Возвращает нужого автора по id")
    @Test
    void getByIdTest() {
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        Author actualAuthor = authorDAOJdbc.getById(ACTUAL_AUTHOR_ID);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(exceptedAuthor);
    }

    @DisplayName("Возвращает всех авторов")
    @Test
    void getAllTest() {
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        List<Author> authors = authorDAOJdbc.getAll();
        assertThat(authors).usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(exceptedAuthor);
    }
}