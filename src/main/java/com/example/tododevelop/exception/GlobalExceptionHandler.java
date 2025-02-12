package com.example.tododevelop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>("Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidException(MethodArgumentNotValidException e) {     //  Valid 관련 예외처리 핸들러

        StringBuilder errorMessage = new StringBuilder("Wrong form");

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(fieldError.getField())
                    .append(" : ")
                    .append(fieldError.getDefaultMessage())
                    .append(" ");
        }

        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleStatusException(ResponseStatusException e) {    // ResponseStatusException 핸들러

        StringBuilder errorMessage = new StringBuilder("Processing failed");

        errorMessage.append(e.getReason() != null ? " " + e.getReason() : " Unknown error");

        return new ResponseEntity<>(errorMessage.toString(), e.getStatusCode());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException e) {    // 로그인 관련 핸들러

        StringBuilder errorMessage = new StringBuilder("Unauthorized Error : ");

        errorMessage.append(e.getMessage() != null ? " " + e.getMessage() : " Unknown error");

        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.UNAUTHORIZED);
    }
}
