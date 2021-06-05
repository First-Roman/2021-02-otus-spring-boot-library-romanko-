package ru.otus.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessError() {
        return ResponseEntity.badRequest().body("У вас нет прав на посещение данного рессурса");
    }
}
