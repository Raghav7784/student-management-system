package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>>
    handleResourceNotFound(
            ResourceNotFoundException ex) {

        logger.error(
                "Resource Not Found: {}",
                ex.getMessage());

        Map<String, Object> error =
                new LinkedHashMap<>();

        error.put("timestamp",
                LocalDateTime.now());

        error.put("status",
                HttpStatus.NOT_FOUND.value());

        error.put("error",
                "Not Found");

        error.put("message",
                ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AllocationException.class)
    public ResponseEntity<Map<String, Object>>
    handleAllocationException(
            AllocationException ex) {

        logger.warn(
                "Allocation Exception: {}",
                ex.getMessage());

        Map<String, Object> error =
                new LinkedHashMap<>();

        error.put("timestamp",
                LocalDateTime.now());

        error.put("status",
                HttpStatus.BAD_REQUEST.value());

        error.put("error",
                "Allocation Error");

        error.put("message",
                ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
    handleGenericException(
            Exception ex) {

        logger.error(
                "System Exception: {}",
                ex.getMessage(),
                ex);

        Map<String, Object> error =
                new LinkedHashMap<>();

        error.put("timestamp",
                LocalDateTime.now());

        error.put("status",
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        error.put("error",
                "Internal Server Error");

        error.put("message",
                ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}