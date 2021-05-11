package ru.otus.library.whithlistener;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ru.otus.library.AbstractMongoRepositoryTest;
import ru.otus.library.events.MongoAuthorCascadeDeleteEventListener;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.repository.author.AuthorRepository;
import ru.otus.library.repository.book.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестируем правильное поведение лисенеров Author")
@Import(MongoAuthorCascadeDeleteEventListener.class)
public class AuthorRepositoryWithListenerTest extends AbstractMongoRepositoryTest {

    private static final int EXPECTED_SIZE_BEFORE_DELETE = 2;
    private static final int EXPECTED_SIZE_AFTER_DELETE = 0;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private final static String ID = "1";

    @DisplayName("Корректное удаление книг, при удалении автора")
    @Test
    public void whenAuthorDeleteCascadeDeleteBook() {
        Author author = authorRepository.findById(ID).orElseThrow();
        List<Book> booksBeforeDelete = bookRepository.findAllBookByAuthor(ID);
        assertThat(booksBeforeDelete.size()).isEqualTo(EXPECTED_SIZE_BEFORE_DELETE);
        authorRepository.delete(author);
        List<Book> booksAfterDelete = bookRepository.findAllBookByAuthor(ID);
        assertThat(booksAfterDelete.size()).isEqualTo(EXPECTED_SIZE_AFTER_DELETE);
    }
}
