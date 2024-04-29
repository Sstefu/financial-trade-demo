package org.assignment.financialtradetool.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.assignment.financialtradetool.constants.ErrorMessages;
import org.assignment.financialtradetool.dto.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Set<Map<String,String>>> handleBindingErrors(MethodArgumentNotValidException exception){
        Set<Map<String,String>> errorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                    return errorMap;
                })
                .collect(Collectors.toSet());
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("Request method not allowed : " + ex.getMethod());
        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED,  ErrorMessages.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        log.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.INTERNAL_ERROR);
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus status, String message) {
        return new ResponseEntity<>(new HttpResponse(status.value(), status, status.getReasonPhrase(), message), status);
    }
}
