package com.project.dp.Exceptions;

import com.project.dp.Exceptions.Classes.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;

@EnableWebMvc
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String INVALID_REQUEST = "Invalid request";
    @ExceptionHandler(value = {NoSuchUserException.class})
    public ResponseEntity<Object> handleNoSuchUserException(NoSuchUserException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), INVALID_REQUEST);
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NoSuchRoleException.class})
    public ResponseEntity<Object> handleNoSuchRoleException(NoSuchRoleException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RoleAlreadyExistsException.class})
    public ResponseEntity<Object> handleRoleAlreadyExistsException(RoleAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NoSuchEmployeeException.class})
    public ResponseEntity<Object> handleNoSuchEmployeeException(NoSuchEmployeeException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmployeeAlreadyExistsException.class})
    public ResponseEntity<Object> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NoSuchSalaryException.class})
    public ResponseEntity<Object> handleNoSuchSalaryException(NoSuchSalaryException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}