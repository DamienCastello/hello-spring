package fr.castello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ FunctionalException.class})
    protected ResponseEntity<String> handleErrors(FunctionalException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleErrors(MethodArgumentNotValidException e){
        List<String> fe = e.getBindingResult().getFieldErrors().stream().map(error->error.getDefaultMessage()).toList();
        //String message = error.getField()+" : "+error.getDefaultMessage();
        return ResponseEntity.badRequest().body(fe);
    }
}