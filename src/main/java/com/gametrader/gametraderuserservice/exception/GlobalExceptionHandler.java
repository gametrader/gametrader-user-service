package com.gametrader.gametraderuserservice.exception;


import com.gametrader.gametraderuserservice.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<ErrorMessage> handleEmailAlreadyTakenException(EmailAlreadyTakenException exception) {
        ErrorMessage message = setUpErrorMessage(HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<ErrorMessage> handleUsernameAlreadyTakenException(UsernameAlreadyTakenException exception) {
        ErrorMessage message = setUpErrorMessage(HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PasswordsDontMatchException.class)
    public ResponseEntity<ErrorMessage> handleEmailAlreadyTakenException(PasswordsDontMatchException exception) {
        ErrorMessage message = setUpErrorMessage(HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }


    private ErrorMessage setUpErrorMessage(int statusCode, String message) {
        return new ErrorMessage(statusCode, LocalDateTime.now(), message);
    }
}
