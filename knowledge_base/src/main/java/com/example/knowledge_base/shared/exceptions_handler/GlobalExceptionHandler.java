package com.example.knowledge_base.shared.exceptions_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.knowledge_base.shared.exceptions.AppException;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleAppException(AppException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
    }

}

@AllArgsConstructor
@Data
class ErrorResponse {
    private String errorCode;
    private String message;
}