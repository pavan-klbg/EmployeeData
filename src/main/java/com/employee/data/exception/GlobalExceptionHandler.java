package com.employee.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValid(MethodArgumentNotValidException ex){
        Map<String, String> errors = new LinkedHashMap<>();
       ex.getBindingResult().getAllErrors().forEach((error)->{
           String fieldName=((FieldError) error).getField();
           String errorMessage=error.getDefaultMessage();
           errors.put(fieldName,errorMessage);
       });
return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }
//
//    // resource not found exception code
//
//    @ExceptionHandler(RuntimeException.class)
//    public void EmployeeNotFoundException(String message){
//        super(message);
//    }
}
