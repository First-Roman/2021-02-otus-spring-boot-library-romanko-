package ru.otus.library.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
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
    private static final Logger LOGGER = Logger.getLogger(BookAPI.class);
    private final LibraryService libraryService;
    private final ConverterBookToBookDTO bookToBookDTO;
    private final ConverterListBookToListBookDTO listBookToListBookDTO;


    @GetMapping(value = "/all")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity getAllBook() {
        List<BookDTO> bookDTOS = listBookToListBookDTO.convert(libraryService.getAllBook());
        return ResponseEntity.ok().body(bookDTOS);
    }

    @GetMapping(value = "/{id}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity getBookById(@PathVariable("id") long id) {
        BookDTO bookDTO = bookToBookDTO.convert(libraryService.getBookById(id));
        return ResponseEntity.ok().body(bookDTO);
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity updateBook(BookDTO bookDTO) {
        libraryService.updateBook(bookDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity addBook(BookDTO bookDTO) {
        libraryService.addBook(bookDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }


    @DeleteMapping(value = "/del/{id}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity delBook(@PathVariable("id") long id) {
        libraryService.removeBookById(id);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

    public ResponseEntity fallBack(Throwable e) {
        LOGGER.error("Превышен лимит времени ожидания!", e);
        return ResponseEntity.status(503).body("Превышен лимит времени ожидания!");
    }
}
