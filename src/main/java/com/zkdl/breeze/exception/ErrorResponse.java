package com.zkdl.breeze.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ErrorResponse {
    @ExceptionHandler(MethodArgumentNotValidException.class) // catch MethodArgumentNotValidException
    public ResponseEntity<ErrorMessage> methodArgument(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMsg = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(ex.getMessage());
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), errorMsg, new Date());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> userAlreadyExist(UserAlreadyExistException ex) {
        String errorMsg = ex.getMessage();
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), errorMsg, new Date());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ProductIsNotExistException.class})
    public ResponseEntity<ErrorMessage> userAlreadyExist(ProductIsNotExistException ex) {
        String errorMsg = ex.getMessage();
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), errorMsg, new Date());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductIsExistInCartException.class})
    public ResponseEntity<ErrorMessage> userAlreadyExist(ProductIsExistInCartException ex) {
        String errorMsg = ex.getMessage();
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), errorMsg, new Date());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ProductIsNotExistInCartException.class})
    public ResponseEntity<ErrorMessage> userAlreadyExist(ProductIsNotExistInCartException ex) {
        String errorMsg = ex.getMessage();
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), errorMsg, new Date());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}