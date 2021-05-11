package ru.otus.library.sevices.comment;

import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentService {
    void addComment(String comment, String nikName, String bookId);

    Comment getCommentById(String id);

    List<Comment> getAllComment();

    List<Comment> getAllCommentForBook(String bookId);

    void updateComment(Comment comment);

    void deleteCommentById(String id);

    void deleteCommentByBookId(String bookId);
}
