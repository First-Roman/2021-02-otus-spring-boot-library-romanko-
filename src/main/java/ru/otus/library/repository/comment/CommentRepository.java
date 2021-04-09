package ru.otus.library.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);

    Comment findById(long id);

    List<Comment> findAllByBookId(long bookId);

    List<Comment> findAll();

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
