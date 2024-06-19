package com.novatec.MSCards.web.controller;

import com.novatec.MSCards.domain.utils.exception.CardAlreadyActiveException;
import com.novatec.MSCards.domain.utils.exception.CardAlreadyBlockedException;
import com.novatec.MSCards.domain.utils.exception.CardAlreadyExistsException;
import com.novatec.MSCards.domain.utils.exception.CardBlockedException;
import com.novatec.MSCards.domain.utils.exception.CardNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Api(value = "Controlador que contiene el manejo de excepciones del microservicio.")
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<String> handleCardAlreadyExists(CardAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<String> handleCardNotFound(CardNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CardAlreadyActiveException.class)
    public ResponseEntity<String> handleCardAlreadyActive(CardAlreadyActiveException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CardBlockedException.class)
    public ResponseEntity<String> handleCardBlocked(CardBlockedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(CardAlreadyBlockedException.class)
    public ResponseEntity<String> handleCardAlreadyBlocked(CardAlreadyBlockedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleMethodValid(HandlerMethodValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
