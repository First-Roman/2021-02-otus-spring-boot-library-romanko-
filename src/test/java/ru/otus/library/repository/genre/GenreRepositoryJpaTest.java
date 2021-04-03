package ru.otus.library.repository.genre;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.models.Genre;

import javax.persistence.NoResultException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестируем репозиторий жанр")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    public static final int EXPECTED_ACTUAL_COUNT = 1;
    public static final int EXCEPTED_NEW_ID = 2;
    public static final String EXCEPTED_GENRE_NAME = "Фэнтези";
    public static final String EXCEPTED_GENRE_NAME_CLASSIC = "Классика";
    public static final int EXCEPTED_ID_CLASSIC = 1;
    public static final int SEQ = 0;

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @Test
    @DisplayName("должен корректно найти по id")
    void findByIdTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        Genre actualGenre = genreRepository.findById(EXCEPTED_ID_CLASSIC);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(exceptedGenre);
    }

    @Test
    @DisplayName("должен корректно сохранить")
    void saveTest() {
        Genre exceptedGenre = new Genre(SEQ, EXCEPTED_GENRE_NAME);
        genreRepository.save(exceptedGenre);
        Genre actualGenre = genreRepository.findById(exceptedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(exceptedGenre);
    }

    @Test
    @DisplayName("должен корректно отдать все записи")
    void findAllTest() {
        Genre exceptedGenre = new Genre(EXCEPTED_ID_CLASSIC, EXCEPTED_GENRE_NAME_CLASSIC);
        List<Genre> actualGenres = genreRepository.findAll();
        assertThat(actualGenres).usingFieldByFieldElementComparator()
                .containsAnyOf(exceptedGenre);
    }

    @Test
    @DisplayName("должен корректно удалить запись")
    void deleteByIdTest() {
        assertThatCode(() -> genreRepository.findById(EXCEPTED_ID_CLASSIC)).doesNotThrowAnyException();
        genreRepository.deleteById(EXCEPTED_ID_CLASSIC);
        assertThatThrownBy(() -> genreRepository.findById(EXCEPTED_ID_CLASSIC)).isInstanceOf(NoResultException.class);
    }
}