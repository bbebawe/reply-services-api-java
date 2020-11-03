package com.reply.streamingservicesapi.controllers;

import com.reply.streamingservicesapi.service.InvalidAgeException;
import com.reply.streamingservicesapi.service.InvalidCardException;
import com.reply.streamingservicesapi.service.InvalidUsernameException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationErrors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
        Error errorDetails = new Error(
                validationErrors);
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Error> handleInvalidGameGuess(
            ValidationException ex,
            WebRequest request) {
        Error err = new Error(List.of(ex.getMessage()));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<Error> handleInvalidAgeException(
            InvalidAgeException ex,
            WebRequest request) {

        Error err = new Error(List.of(ex.getMessage()));
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<Error> handleInvalidUsernameException(
            InvalidUsernameException ex,
            WebRequest request) {

        Error err = new Error(List.of(ex.getMessage()));
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCardException.class)
    public ResponseEntity<Error> handleInvalidCardException(
            InvalidCardException ex,
            WebRequest request) {

        Error err = new Error(List.of(ex.getMessage()));
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

}
