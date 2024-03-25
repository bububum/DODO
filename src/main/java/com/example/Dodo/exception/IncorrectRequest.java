package com.example.Dodo.exception;

public class IncorrectRequest extends RuntimeException{

    public IncorrectRequest(String message) {
        super(message);
    }
}
