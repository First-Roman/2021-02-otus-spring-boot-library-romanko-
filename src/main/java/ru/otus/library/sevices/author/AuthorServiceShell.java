package ru.otus.library.sevices.author;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.author.AuthorDAO;
import ru.otus.library.domain.Author;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceShell implements AuthorService {
    private final AuthorDAO authorDAO;

    @Override
    public void addAuthor(String firstName, String lastName, String middleName) {
        long id = authorDAO.count() + 1;
        Author author = new Author(id, firstName, lastName, middleName);
        authorDAO.insert(author);
    }

    @Override
    public Author getAuthorById(long id) {
        return authorDAO.getById(id);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorDAO.getAll();
    }
}
