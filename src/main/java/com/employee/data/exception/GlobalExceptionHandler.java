package com.employee.data.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        Throwable cause = ex.getMostSpecificCause();
        String message;
        if (cause instanceof InvalidFormatException invalidFormat) {
            // Field exists but wrong type
            String fieldName = invalidFormat.getPath().stream()
                    .map(ref -> ref.getFieldName())
                    .reduce((first, second) -> second)  // get last field in path
                    .orElse("unknown");
            String targetType = invalidFormat.getTargetType().getSimpleName();
            message = String.format("Invalid value for field '%s'. Expected type: %s.", fieldName, targetType);

        } else if (cause instanceof MismatchedInputException mismatch) {
            // Field missing or value missing
            String fieldName = mismatch.getPath().isEmpty() ? "unknown field"
                    : mismatch.getPath().stream()
                    .map(ref -> ref.getFieldName())
                    .reduce((first, second) -> second)
                    .orElse("unknown field");
            message = String.format("Missing or invalid value for field '%s'.", fieldName);

        } else {
            message = "Malformed JSON request. Please check your request body.";
        }
        error.put("error", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

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
//
}
