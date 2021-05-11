package ru.otus.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.library.controller.simple.Response;

@ControllerAdvice
public class AdviceLibraryController {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity exceptionHandler(NullPointerException e) {
        System.out.println("Ошибка в работе сервиса" + e);
        return ResponseEntity.badRequest().body(Response.BAD.getName());
    }
}
