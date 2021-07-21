package ru.otus.library.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.controller.simple.Response;
import ru.otus.library.convertor.author.ConverterAuthorToAuthorDTO;
import ru.otus.library.convertor.author.ConverterListAuthorToListAuthorDTO;
import ru.otus.library.dto.AuthorDTO;
import ru.otus.library.sevices.library.LibraryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/author", produces = "application/json")
@RequiredArgsConstructor
public class AuthorAPI {
    private static final Logger LOGGER = Logger.getLogger(AuthorAPI.class);
    private final LibraryService libraryService;
    private final ConverterAuthorToAuthorDTO authorToAuthorDTO;
    private final ConverterListAuthorToListAuthorDTO listAuthorToListAuthorDTO;


    @GetMapping("/all")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity getAllAuthor() {
        List<AuthorDTO> authorDTOS = listAuthorToListAuthorDTO.convert(libraryService.getAllAuthor());
        return ResponseEntity.ok().body(authorDTOS);
    }


    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity getAuthorById(@PathVariable("id") long id) {
        AuthorDTO authorDTO = authorToAuthorDTO.convert(libraryService.getAuthorById(id));
        return ResponseEntity.ok().body(authorDTO);
    }


    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity updateAuthor(AuthorDTO authorDTO) {
        libraryService.updateAuthor(authorDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }


    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity addAuthor(AuthorDTO authorDTO) {
        libraryService.addAuthor(authorDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }


    @DeleteMapping(value = "/del/{id}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity deleteAuthor(@PathVariable("id") long id) {
        libraryService.removeAuthorById(id);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

    public ResponseEntity fallBack(Throwable e) {
        LOGGER.error("Превышен лимит времени ожидания!", e);
        return ResponseEntity.status(503).body("Превышен лимит времени ожидания!");
    }

}
