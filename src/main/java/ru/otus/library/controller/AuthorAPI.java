package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
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

    private final LibraryService libraryService;
    private final ConverterAuthorToAuthorDTO authorToAuthorDTO;
    private final ConverterListAuthorToListAuthorDTO listAuthorToListAuthorDTO;


    @GetMapping("/all")
    public ResponseEntity getAllAuthor() {
        List<AuthorDTO> authorDTOS = listAuthorToListAuthorDTO.convert(libraryService.getAllAuthor());
        return ResponseEntity.ok().body(authorDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity getAuthorById(@PathVariable("id") long id) {
        AuthorDTO authorDTO = authorToAuthorDTO.convert(libraryService.getAuthorById(id));
        return ResponseEntity.ok().body(authorDTO);
    }


    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public ResponseEntity updateAuthor(AuthorDTO authorDTO) {
        libraryService.updateAuthor(authorDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }


    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity addAuthor(AuthorDTO authorDTO) {
        libraryService.addAuthor(authorDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }


    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") long id) {
        libraryService.removeAuthorById(id);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

}
