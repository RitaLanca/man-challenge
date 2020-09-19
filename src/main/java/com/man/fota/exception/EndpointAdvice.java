package com.man.fota.exception;

import com.man.fota.domain.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EndpointAdvice {

    @ExceptionHandler(VinNotFoundException.class)
    public final ResponseEntity<Resource> handleVinNotFoundException(VinNotFoundException ex) {
        Resource resource = new Resource(ex.getMessage(), null);
        return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
    }
}
