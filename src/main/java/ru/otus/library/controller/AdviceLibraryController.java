package ru.otus.library.controller;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.library.controller.simple.Response;

@ControllerAdvice
public class AdviceLibraryController {
    private static final Logger LOGGER = Logger.getLogger(AdviceLibraryController.class);

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity exceptionHandler(NullPointerException e) {
        LOGGER.error("Ошибка в работе сервиса ", e);
        return ResponseEntity.badRequest().body(Response.BAD.getName());
    }
}
