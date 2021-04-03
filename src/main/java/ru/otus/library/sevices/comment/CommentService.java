package ru.otus.library.sevices.comment;

import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentService {
    void addComment(String comment, String nikName, long bookId);

    Comment getCommentById(long id);

    List<Comment> getAllComment();

    List<Comment> getAllCommentForBook(long bookId);

    void updateComment(Comment comment);

    void deleteCommentById(long id);

    void deleteCommentByBookId(long bookId);
}
