package ru.otus.library.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.controller.simple.Response;
import ru.otus.library.convertor.genre.ConverterGenreToGenreDTO;
import ru.otus.library.convertor.genre.ConverterListGenreToListGenreDTO;
import ru.otus.library.dto.GenreDTO;
import ru.otus.library.sevices.library.LibraryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/genre", produces = "application/json")
@RequiredArgsConstructor
public class GenreAPI {
    private static final Logger LOGGER = Logger.getLogger(GenreAPI.class);
    private final LibraryService libraryService;
    private final ConverterGenreToGenreDTO genreToGenreDTO;
    private final ConverterListGenreToListGenreDTO listGenreToListGenreDTO;


    @GetMapping("/all")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity getAllGenre() {
        List<GenreDTO> genreDTOS = listGenreToListGenreDTO.convert(libraryService.getAllGenre());
        return ResponseEntity.ok().body(genreDTOS);
    }


    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity getGenreById(@PathVariable("id") long id) {
        GenreDTO genreDTO = genreToGenreDTO.convert(libraryService.getGenreById(id));
        return ResponseEntity.ok().body(genreDTO);
    }


    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity updateGenre(GenreDTO genreDTO) {
        libraryService.updateGenre(genreDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }


    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity addGenre(GenreDTO genreDTO) {
        libraryService.addGenre(genreDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }


    @DeleteMapping(value = "/del/{id}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public ResponseEntity deleteGenre(@PathVariable("id") long id) {
        libraryService.removeGenreById(id);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

    public ResponseEntity fallBack(Throwable e) {
        LOGGER.error("Превышен лимит времени ожидания!", e);
        return ResponseEntity.status(503).body("Превышен лимит времени ожидания!");
    }

}
