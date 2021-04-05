package ru.otus.library.repository.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.models.Author;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестируем репозиторий авторов")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    public static final int ACTUAL_AUTHOR_ID = 1;
    public static final String EXCEPTED_FIRST_NAME = "Лев";
    public static final String EXCEPTED_LAST_NAME = "Толстой";
    public static final String EXCEPTED_MIDDLE_NAME = "Николаевич";
    public static final int EXCEPTED_NEW_ID = 0;
    public static final String EXCEPTED_NEW_FIRST_NAME = "Александр";
    public static final String EXCEPTED_NEW_LAST_NAME = "Пушкин";
    public static final String EXCEPTED_NEW_MIDDLE_NAME = "Сергеевич";

    @Autowired
    private AuthorRepositoryJpa authorRepository;

    @Test
    @DisplayName("должен корректно найти по id")
    void findByIdTest() {
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        List<Author> authors = authorRepository.findAll();
        Author actualAuthor = authorRepository.findById(exceptedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(exceptedAuthor);
    }

    @Test
    @DisplayName("должен корректно отдать все записи")
    void findAllTest() {
        Author exceptedAuthor = new Author(ACTUAL_AUTHOR_ID, EXCEPTED_FIRST_NAME, EXCEPTED_LAST_NAME, EXCEPTED_MIDDLE_NAME);
        List<Author> actualAuthors = authorRepository.findAll();
        assertThat(actualAuthors).usingFieldByFieldElementComparator().containsAnyOf(exceptedAuthor);
    }

    @Test
    @DisplayName("должен корректно сохранить")
    void saveTest() {
        Author exceptedAuthor = new Author(EXCEPTED_NEW_ID, EXCEPTED_NEW_FIRST_NAME, EXCEPTED_NEW_LAST_NAME, EXCEPTED_NEW_MIDDLE_NAME);
        authorRepository.save(exceptedAuthor);
        Author actualAuthor = authorRepository.findById(exceptedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(exceptedAuthor);
    }


    @Test
    @DisplayName("должен корректно удалить запись")
    void deleteByIdTest() {
        assertThatCode(() -> authorRepository.findById(ACTUAL_AUTHOR_ID)).doesNotThrowAnyException();
        authorRepository.deleteById(ACTUAL_AUTHOR_ID);
        assertThatThrownBy(() -> Optional.ofNullable(authorRepository.findById(ACTUAL_AUTHOR_ID)).orElseThrow()).isInstanceOf(NoSuchElementException.class);
    }
}