package ru.otus.library.repository.comment;

import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);

    Comment findById(long id);

    List<Comment> findAllByBookId(long bookId);

    List<Comment> findAll();

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
