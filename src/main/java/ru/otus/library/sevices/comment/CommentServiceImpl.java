package ru.otus.library.sevices.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.models.Comment;
import ru.otus.library.repository.comment.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    @Override
    public void addComment(String comment, String nikName, long bookId) {
        Comment cm = new Comment(0, comment, nikName, bookId);
        commentRepository.save(cm);
    }

    @Override
    public Comment getCommentById(long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getAllCommentForBook(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteCommentByBookId(long bookId) {
        commentRepository.deleteByBookId(bookId);
    }
}
