package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(
            UserNotFoundException ex) {

        return buildResponse(ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<Object> handleProjectNotFound(
            ProjectNotFoundException ex) {

        return buildResponse(ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SkillNotFoundException.class)
    public ResponseEntity<Object> handleSkillNotFound(
            SkillNotFoundException ex) {

        return buildResponse(ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AllocationException.class)
    public ResponseEntity<Object> handleAllocationException(
            AllocationException ex) {

        return buildResponse(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(
            AuthorizationDeniedException ex) {

        return buildResponse("Access Denied",
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(
            MethodArgumentNotValidException ex) {

        String errorMessage =
                ex.getBindingResult()
                  .getFieldError()
                  .getDefaultMessage();

        return buildResponse(errorMessage,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(
            Exception ex) {

        return buildResponse(ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(
            ResourceNotFoundException ex) {

        return buildResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponse(
            String message,
            HttpStatus status) {

        Map<String, Object> body =
                new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }
}