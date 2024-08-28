package com.ednaldo.rest_api_spring_boot_and_java.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import java.time.Instant;
import java.util.Date;

@RestControllerAdvice

public class ResourceExceptionHendler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

//    @ExceptionHandler(DataBaseException.class)
//    public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request) {
//        String error = "Database error";
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
//        return ResponseEntity.status(status).body(standardError);
//    }
}
