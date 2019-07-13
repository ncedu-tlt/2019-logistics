package ru.ncedu.logistics.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.ncedu.logistics.api.exception.EntityNotFoundException;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<String> handleEntityNotFound(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
