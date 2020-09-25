package com.twuc.shopping.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Error> handleNotValidException(Exception e) {

        String message;

        if (e instanceof MethodArgumentNotValidException) {
            message = "invalid param";
        } else {
            message = e.getMessage();
        }

        return ResponseEntity.badRequest().body(new Error(message));
    }

}
