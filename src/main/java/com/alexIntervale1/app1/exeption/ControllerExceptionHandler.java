package com.alexIntervale1.app1.exeption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomAppException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ErrorMessage customAppException(CustomAppException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.OK.value(),
                ex.getMessage(),
                request.getDescription(false));

        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ErrorMessage allAppException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.OK.value(),
                ex.getMessage(),
                request.getDescription(false));

        return message;
    }
}
