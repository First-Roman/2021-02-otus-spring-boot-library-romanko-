package ru.otus.library.sevices.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.models.Author;
import ru.otus.library.repository.author.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void addAuthor(String firstName, String lastName, String middleName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setMiddleName(middleName);
        author.setLastName(lastName);
        authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(String id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthorById(String id) {
        authorRepository.deleteById(id);
    }
}
