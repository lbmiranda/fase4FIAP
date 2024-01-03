package com.fase4FIAP.streaming.aplicacao.exceptions;

import com.fase4FIAP.streaming.aplicacao.exceptions.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException ex) {
        var details = new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.append(errorMessage).append("; ");
        });

        var details = new ErrorDetails(errors.toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyFavoritedException.class)
    public ResponseEntity<ErrorDetails> handleAlreadyFavoritedException(AlreadyFavoritedException ex) {
        var details = new ErrorDetails(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
