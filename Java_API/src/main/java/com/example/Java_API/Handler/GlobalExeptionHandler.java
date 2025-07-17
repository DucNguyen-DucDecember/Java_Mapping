package com.example.Java_API.Handler;

import com.example.Java_API.exception.UserNotFoundException;
import com.example.Java_API.response.ErrorDomain;
import com.example.Java_API.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExeptionHandler {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ErrorResponse handle(final UserNotFoundException exception){
        return ErrorResponse.of(exception.getMessage()
                , HttpStatus.NOT_FOUND.value()
                , exception.getErrorCode()
                ,exception.getDomain());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ErrorResponse handle(final Exception exception){
        exception.printStackTrace();
        return ErrorResponse.of(exception.getMessage()
                , HttpStatus.INTERNAL_SERVER_ERROR.value()
                , "server"
                , ErrorDomain.SERVER);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ErrorResponse handle(final ConstraintViolationException exception){
        Map<String, Object> errors = exception.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(x->x.getPropertyPath().toString(), x->x.getMessage(), (a, b) -> a));
        return ErrorResponse.of("bad request"
                , HttpStatus.BAD_REQUEST.value()
                , "client"
                , ErrorDomain.INVALID, errors);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ErrorResponse handle(final MethodArgumentNotValidException exception){
        Map<String, Object> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(x->x.getField(), x->x.getDefaultMessage(), (a, b) -> a));
        return ErrorResponse.of("bad request"
                , HttpStatus.BAD_REQUEST.value()
                , "client"
                , ErrorDomain.INVALID, errors);
    }
}
