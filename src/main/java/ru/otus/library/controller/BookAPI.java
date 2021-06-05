package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.controller.simple.Response;
import ru.otus.library.convertor.book.ConverterBookToBookDTO;
import ru.otus.library.convertor.book.ConverterListBookToListBookDTO;
import ru.otus.library.dto.BookDTO;
import ru.otus.library.sevices.library.LibraryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book", produces = "application/json")
@RequiredArgsConstructor
public class BookAPI {

    private final LibraryService libraryService;
    private final ConverterBookToBookDTO bookToBookDTO;
    private final ConverterListBookToListBookDTO listBookToListBookDTO;


    @GetMapping(value = "/all")
    public ResponseEntity getAllBook() {
        List<BookDTO> bookDTOS = listBookToListBookDTO.convert(libraryService.getAllBook());
        return ResponseEntity.ok().body(bookDTOS);
    }

    @GetMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity getBookById(@PathVariable("id") long id) {
        BookDTO bookDTO = bookToBookDTO.convert(libraryService.getBookById(id));
        return ResponseEntity.ok().body(bookDTO);
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    @Secured("ROLE_ADMIN")
    public ResponseEntity updateBook(BookDTO bookDTO) {
        libraryService.updateBook(bookDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    @Secured("ROLE_ADMIN")
    public ResponseEntity addBook(BookDTO bookDTO) {
        libraryService.addBook(bookDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }


    @DeleteMapping(value = "/del/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity delBook(@PathVariable("id") long id) {
        libraryService.removeBookById(id);
        return ResponseEntity.ok().body(Response.OK.getName());
    }
}
