package com.ruslan.validation.service.mapper.exception;


import com.ruslan.validation.dto.ObjAdditionResponse;
import com.ruslan.validation.dto.validation.ErrorResponse;
import com.ruslan.validation.dto.validation.FieldErrorDto;
import com.ruslan.validation.dto.validation.ValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handle(MethodArgumentNotValidException ex) {
        List<FieldErrorDto> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> new FieldErrorDto(e.getField(), e.getDefaultMessage()))
                .toList();

        ValidationErrorDto responseBody = new ValidationErrorDto(HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseBody);
    }

    @ExceptionHandler(NoSuchEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(NoSuchEntityException ex) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

//    @ExceptionHandler(ObjAdditionResponse.class)
//    @ResponseStatus(HttpStatus.OK)
//    public ObjAdditionResponse handle(RuntimeException ex) {
//        return new ObjAdditionResponse(true, ex.getMessage());
//    }
}
