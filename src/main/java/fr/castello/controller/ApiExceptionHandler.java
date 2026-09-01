package fr.castello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ FunctionalException.class})
    protected ResponseEntity<String> handleErrors(FunctionalException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}