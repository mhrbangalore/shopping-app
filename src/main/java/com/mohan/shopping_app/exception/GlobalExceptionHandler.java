package com.mohan.shopping_app.exception;

import com.mohan.shopping_app.model.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductAlreadyExists.class)
    public ResponseEntity<ErrorMessage> handleProductAlreadyExists(HttpServletRequest request, ProductAlreadyExists ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorMessage> handleProductNotFound(HttpServletRequest request, ProductNotFound ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoProductsWithName.class)
    public ResponseEntity<ErrorMessage> handleNoProductsWithName(HttpServletRequest request, NoProductsWithName ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotInStock.class)
    public ResponseEntity<ErrorMessage> handleProductNotFound(HttpServletRequest request, ProductNotInStock ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleArgInvalidException(HttpServletRequest request, MethodArgumentNotValidException ex){
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return new ResponseEntity<>(new ErrorMessage("Input Validation failed", HttpStatus.BAD_REQUEST.toString(), request.getRequestURI(), validationErrors), HttpStatus.BAD_REQUEST);
    }
}
