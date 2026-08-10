package com.lsouzadev.aula.exceptions.handlers;

import com.lsouzadev.aula.dto.CustomError;
import com.lsouzadev.aula.dto.ErroCampo;
import com.lsouzadev.aula.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandlers {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomError> notFoundExceptionHandler(NotFoundException ex,
                                                                HttpServletRequest request) {
        CustomError customError = CustomError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .errorMessage(ex.getMessage())
                .errors(List.of())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex,
                                                                              HttpServletRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ErroCampo> errors = fieldErrors.stream().map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage())).toList();
        CustomError customError = CustomError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage("Erro de validacao")
                .errors(errors)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customError);
    }
}
