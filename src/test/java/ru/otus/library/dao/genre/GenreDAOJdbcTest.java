package ru.otus.library.dao.genre;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Тестируем DAO Genre")
@JdbcTest
@Import(GenreDAOJdbc.class)
class GenreDAOJdbcTest {

    public static final int EXPECTED_ACTUAL_COUNT = 1;
    public static final int EXCEPTED_NEW_ID = 2;
    public static final String EXCEPTED_GENRE_NAME = "Фэнтези";
    public static final String EXCEPTED_GENRE_NAME_CLASSIC = "Классика";
    public static final int EXCEPTED_ID_CLASSIC = 1;
    @Autowired
    GenreDAOJdbc genreDAOJdbc;

    @DisplayName("Возвращает колличество жанров")
    @Test
    void countTest() {
        int actualGenreCount = genreDAOJdbc.count();
        assertThat(actualGenreCount).isEqualTo(EXPECTED_ACTUAL_COUNT);
    }

    @DisplayName("Создает новый жанр")
    @Test
    void insertTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_NEW_ID, EXCEPTED_GENRE_NAME);
        genreDAOJdbc.insert(exceptedGenre);
        Genre actualGenre = genreDAOJdbc.getById(exceptedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(exceptedGenre);
    }

    @DisplayName("Возвращает жанр по id")
    @Test
    void getByIdTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Genre actualGenre = genreDAOJdbc.getById(EXCEPTED_ID_CLASSIC);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(exceptedGenre);
    }

    @DisplayName("Возвращает жанры")
    @Test
    void getAllTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        List<Genre> actualGenres = genreDAOJdbc.getAll();
        assertThat(actualGenres).usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(exceptedGenre);
    }
}