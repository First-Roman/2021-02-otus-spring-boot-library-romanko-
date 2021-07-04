package ru.otus.library.controller;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceSecurityController {
    private final static Logger LOGGER = Logger.getLogger(AdviceSecurityController.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessError(AccessDeniedException e) {
        LOGGER.error("Попытка посетить ресурс без прав ", e);
        return ResponseEntity.badRequest().body("У вас нет прав на посещение данного рессурса");
    }
}
