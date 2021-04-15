package ru.otus.library.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBookId(long bookId);

    void deleteByBookId(long bookId);
}
