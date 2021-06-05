package ru.otus.library.whithlistener;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ru.otus.library.AbstractMongoRepositoryTest;
import ru.otus.library.events.MongoBookCascadeDeleteEventListener;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.comment.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тестируем правильное поведение лисенеров Book")
@Import(MongoBookCascadeDeleteEventListener.class)
public class BookRepositoryWithListenerTest extends AbstractMongoRepositoryTest {

    private static final int EXPECTED_SIZE_BEFORE_DELETE = 1;
    private static final int EXPECTED_SIZE_AFTER_DELETE = 0;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    private final static String ID = "1";

    @Test
    @DisplayName("Удаляются комментарии к книге, при удалении книги.")
    public void whenBookDeleteCascadeDeleteComment() {
        Book book = bookRepository.findById(ID).orElseThrow();
        List<Comment> commentsBeforeDelete = commentRepository.findAllByBookId(ID);
        assertThat(commentsBeforeDelete.size()).isEqualTo(EXPECTED_SIZE_BEFORE_DELETE);
        bookRepository.delete(book);
        List<Comment> commentsAfterDelete = commentRepository.findAllByBookId(ID);
        assertThat(commentsAfterDelete.size()).isEqualTo(EXPECTED_SIZE_AFTER_DELETE);
    }
}
