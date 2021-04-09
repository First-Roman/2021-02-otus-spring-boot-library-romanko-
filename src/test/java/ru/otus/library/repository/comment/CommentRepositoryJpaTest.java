package ru.otus.library.repository.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.models.Comment;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестируем репозиторий комментариев")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    public static final int EXCEPTED_ID = 1;
    public static final String EXCEPTED_COMMENT = "GOOD BOOK";
    public static final String EXCEPTED_NIK = "DAN";
    public static final int EXCEPTED_BOOK_ID = 2;
    public static final int SEQ = 0;
    public static final String EXCEPT_NEW_COMMENT = "Great!";
    @Autowired
    private CommentRepositoryJpa commentRepository;

    @Test
    @DisplayName("должен корректно найти по id")
    void findByIdTest() {
        Comment exceptedComment = new Comment(EXCEPTED_ID, EXCEPTED_COMMENT, EXCEPTED_NIK, EXCEPTED_BOOK_ID);
        Comment actualComment = commentRepository.findById(exceptedComment.getId());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(exceptedComment);
    }

    @Test
    @DisplayName("должен корректно сохранить")
    void saveTest() {
        Comment exceptedComment = new Comment(SEQ, EXCEPTED_COMMENT, EXCEPTED_NIK, EXCEPTED_BOOK_ID);
        commentRepository.save(exceptedComment);
        Comment actualComment = commentRepository.findById(exceptedComment.getId());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(exceptedComment);
    }

    @Test
    @DisplayName("должен корректно обновлять")
    void updateTest() {
        Comment exceptedComment = new Comment(EXCEPTED_ID, EXCEPT_NEW_COMMENT, EXCEPTED_NIK, EXCEPTED_BOOK_ID);
        commentRepository.save(exceptedComment);
        Comment actualComment = commentRepository.findById(exceptedComment.getId());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(exceptedComment);
    }

    @Test
    @DisplayName("должен корректно отдать все записи по конкретной книге")
    void findAllByBookIdTest() {
        Comment exceptedComment = new Comment(EXCEPTED_ID, EXCEPTED_COMMENT, EXCEPTED_NIK, EXCEPTED_BOOK_ID);
        List<Comment> actualComments = commentRepository.findAllByBookId(EXCEPTED_BOOK_ID);
        assertThat(actualComments).usingFieldByFieldElementComparator()
                .containsAnyOf(exceptedComment);
    }

    @Test
    @DisplayName("должен корректно отдать все записи")
    void findAllTest() {
        Comment exceptedComment = new Comment(EXCEPTED_ID, EXCEPTED_COMMENT, EXCEPTED_NIK, EXCEPTED_BOOK_ID);
        List<Comment> actualComments = commentRepository.findAll();
        assertThat(actualComments).usingFieldByFieldElementComparator()
                .containsAnyOf(exceptedComment);
    }

    @Test
    @DisplayName("должен корректно удалить запись")
    void deleteByIdTest() {
        assertThatCode(() -> commentRepository.findById(EXCEPTED_ID)).doesNotThrowAnyException();
        commentRepository.deleteById(EXCEPTED_ID);
        assertThatThrownBy(() -> Optional.ofNullable(commentRepository.findById(EXCEPTED_ID)).orElseThrow()).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("должен корректно удалить комментарии по книге")
    void deleteByBookIdTest() {
        List<Comment> commentsAfter = commentRepository.findAllByBookId(EXCEPTED_BOOK_ID);
        assertThat(commentsAfter).isNotNull().hasSize(2);
        commentRepository.deleteByBookId(EXCEPTED_BOOK_ID);
        List<Comment> commentsBefore = commentRepository.findAllByBookId(EXCEPTED_BOOK_ID);
        assertThat(commentsBefore).isNotNull().hasSize(0);
    }
}