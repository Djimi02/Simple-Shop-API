package com.example.simple_shop.helpers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerExceptionHandler {

    /**
     * If any of the specified, in the annotation, exceptions appears in any of the controllers,
     * the response of the controller will be 400 (Bad Request) with the exception message
     * inside the body of the response.
     */
    @ExceptionHandler(value = {IllegalArgumentException.class, RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(Exception ex) {
        String bodyOfResponse = null;
        if (ex instanceof IllegalAccessException) { // One of my exceptions
            bodyOfResponse = ex.getMessage();
        } else { // Any other
            bodyOfResponse = "Unexpected exception occurred!";
        }
        
        return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
}
