package ru.otus.library.sevices.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Author;
import ru.otus.library.repository.author.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void addAuthor(String firstName, String lastName, String middleName) {
        Author author = new Author(0, firstName, lastName, middleName);
        authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAuthorById(long id) {
        authorRepository.deleteById(id);
    }
}
