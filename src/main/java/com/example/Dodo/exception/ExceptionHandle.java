package com.example.Dodo.exception;

import com.example.Dodo.model.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandle {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        return new ResponseEntity(new Response(errors, ex.getMessage()), HttpStatus.MULTI_STATUS);
    }

    @ExceptionHandler(IncorrectRequest.class)
    public ResponseEntity<?> exceptionHandle(IncorrectRequest e) {
        return new ResponseEntity(new Response(null, e.getMessage()), HttpStatus.I_AM_A_TEAPOT);
    }
}