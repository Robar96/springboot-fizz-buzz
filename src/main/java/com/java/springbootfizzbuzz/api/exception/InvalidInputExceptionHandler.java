package com.java.springbootfizzbuzz.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class InvalidInputExceptionHandler {
    //The exception handler catches the MethodArgumentTypeMismatchException exception
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<List<String>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        //Generates an error message for the invalid input value, returned as a list of strings(for the json format) in the HTTP response.
        String errorMessage = "Invalid input: " + ex.getName() + " should be of type " + ex.getRequiredType().getSimpleName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorMessage));
    }
}