package com.addressbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sharatjagannath on 7/26/17.
 */
@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    public ControllerExceptionHandler() {

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UniquenessException.class)
    public String handleUniquenessException(UniquenessException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingDataException.class)
    public String handleMissingDataException(MissingDataException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataException.class)
    public String handleInvalidDataException(InvalidDataException e) {
        return e.getMessage();
    }
}
