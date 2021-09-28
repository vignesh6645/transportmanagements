package com.example.TransportManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandler extends RuntimeException {

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> HandleException(NoSuchElementException  e){
        return new ResponseEntity<Object>("There is no value present here..", HttpStatus.NOT_FOUND);
    }

}
