package com.lsouzadev.aula.exceptions.handlers;

import com.lsouzadev.aula.dto.CustomError;
import com.lsouzadev.aula.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandlers {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomError> notFoundExceptionHandler(NotFoundException ex, HttpServletRequest request) {
        CustomError customError = CustomError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
    }
}
