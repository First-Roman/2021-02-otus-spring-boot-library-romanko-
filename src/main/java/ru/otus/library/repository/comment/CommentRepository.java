package ru.otus.library.repository.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String bookId);

    void deleteByBookId(String bookId);

}
