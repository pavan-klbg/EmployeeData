package com.employee.data.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
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
        error.put("error", buildErrorMessage(ex));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private String buildErrorMessage(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof InvalidFormatException invalidFormat) {
            return buildInvalidFormatMessage(invalidFormat);
        } else if (cause instanceof MismatchedInputException mismatch) {
            return buildMismatchedInputMessage(mismatch);
        }
        return "Malformed JSON request. Please check your request body.";
    }

    @ExceptionHandler(InvalidFormatException.class)
    private String buildInvalidFormatMessage(InvalidFormatException invalidFormat) {
        String fieldName = invalidFormat.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .reduce((first, second) -> second)
                .orElse("unknown");
        String targetType = invalidFormat.getTargetType().getSimpleName();
        return String.format("Invalid value for field '%s'. Expected type: %s.", fieldName, targetType);
    }

    @ExceptionHandler(MismatchedInputException.class)
    private String buildMismatchedInputMessage(MismatchedInputException mismatch) {
        String fieldName = mismatch.getPath().isEmpty() ? "unknown field"
                : mismatch.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .reduce((first, second) -> second)
                .orElse("unknown field");
        return String.format("Missing or invalid value for field '%s'.", fieldName);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
}
