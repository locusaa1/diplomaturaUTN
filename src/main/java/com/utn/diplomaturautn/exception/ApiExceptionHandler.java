package com.utn.diplomaturautn.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //Annotation that allows us to centralize the exception handle of the api
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) //Annotation that sets the response status for the method
    @ResponseBody
    public ErrorObject handleResourceNotFoundException(Exception exception) {

        return ErrorObject.builder().
                statusCode(HttpStatus.NOT_FOUND.value()).
                message(exception.getMessage()).
                timestamp(Time.valueOf(LocalTime.now())).build();
    }

    @ExceptionHandler({
            SQLIntegrityConstraintViolationException.class,
            NothingToModifyException.class,
            InvalidCallException.class,
            ErrorSavingEntityException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorObject handleConflictsException(Exception exception) {

        return ErrorObject.builder().
                statusCode(HttpStatus.CONFLICT.value()).
                message(exception.getMessage()).
                timestamp(Time.valueOf(LocalTime.now())).build();
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void handleNoContentException() {

    }

    @ExceptionHandler({
            InvalidBeanFieldsException.class,
            InvalidPhoneException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorObject handleBadRequestException(Exception exception) {

        return ErrorObject.builder().
                statusCode(HttpStatus.BAD_REQUEST.value()).
                message(exception.getMessage()).
                timestamp(Time.valueOf(LocalTime.now())).build();
    }

    @Override
    public @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().
                forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });

        return new ResponseEntity<>(ErrorObject.builder().
                statusCode(HttpStatus.BAD_REQUEST.value()).
                message(errors.toString()).
                timestamp(Time.valueOf(LocalTime.now())).build(), HttpStatus.BAD_REQUEST);
    }
}
