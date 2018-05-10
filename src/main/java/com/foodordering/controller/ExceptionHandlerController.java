package com.foodordering.controller;

import com.foodordering.dto.ExceptionResponse;
import com.foodordering.exception.DataBindingErrorMessage;
import com.foodordering.exception.QuantityException;
import com.foodordering.exception.UnauthorizedExceptionHandler;
import com.foodordering.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UnauthorizedExceptionHandler.class)
    public ResponseEntity<ExceptionResponse> unAthorizedException(final UnauthorizedExceptionHandler ex, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(ex.getMessage());
        error.setCallerUrl(request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(final UserNotFoundException ex, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(ex.getMessage());
        error.setCallerUrl(request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuantityException.class)
    public ResponseEntity<ExceptionResponse> quantityException(final QuantityException ex, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(ex.getMessage());
        error.setCallerUrl(request.getRequestURI());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataBindingErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        exception.printStackTrace();
        return dataBindingErrorMessagesConverter(exception.getBindingResult());
    }

    public DataBindingErrorMessage dataBindingErrorMessagesConverter(BindingResult bindingResult) {
        DataBindingErrorMessage dataBindingErrorMessage = new DataBindingErrorMessage();
        dataBindingErrorMessage.setCode(HttpStatus.BAD_REQUEST.value());
        dataBindingErrorMessage.setErrorMessage("Invalid request parameter");
        List<DataBindingErrorMessage.Error> errors = new ArrayList<>();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for(FieldError fieldError:fieldErrors){
            DataBindingErrorMessage.Error error = dataBindingErrorMessage.new Error();
            error.setErrorMessage(fieldError.getDefaultMessage());
            error.setRejectedValue(fieldError.getRejectedValue());
            error.setFieldName(fieldError.getField());
            errors.add(error);
        }
        dataBindingErrorMessage.setErrors(errors);
        return dataBindingErrorMessage;
    }
}