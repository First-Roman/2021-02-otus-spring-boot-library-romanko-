package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.controller.simple.Response;
import ru.otus.library.convertor.genre.ConverterGenreToGenreDTO;
import ru.otus.library.convertor.genre.ConverterListGenreToListGenreDTO;
import ru.otus.library.dto.GenreDTO;
import ru.otus.library.sevices.library.LibraryService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/genre", produces = "application/json")
@RequiredArgsConstructor
public class GenreAPI {

    private final LibraryService libraryService;
    private final ConverterGenreToGenreDTO genreToGenreDTO;
    private final ConverterListGenreToListGenreDTO listGenreToListGenreDTO;

    @GetMapping("/all")
    public ResponseEntity getAllGenre() {
        List<GenreDTO> genreDTOS = listGenreToListGenreDTO.convert(libraryService.getAllGenre());
        return ResponseEntity.ok().body(genreDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity getGenreById(@PathVariable("id") long id) {
        GenreDTO genreDTO = genreToGenreDTO.convert(libraryService.getGenreById(id));
        return ResponseEntity.ok().body(genreDTO);
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public ResponseEntity updateGenre(GenreDTO genreDTO) {
        libraryService.updateGenre(genreDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity addGenre(GenreDTO genreDTO) {
        libraryService.addGenre(genreDTO);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity deleteGenre(@PathVariable("id") long id) {
        libraryService.removeGenreById(id);
        return ResponseEntity.ok().body(Response.OK.getName());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity exceptionHandler(IOException e) {
        System.out.println("Ошибка в работе сервиса  genre " + e);
        return ResponseEntity.badRequest().body(Response.BAD.getName());
    }
}
