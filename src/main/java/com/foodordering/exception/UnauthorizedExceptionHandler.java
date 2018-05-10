package com.foodordering.exception;

public class UnauthorizedExceptionHandler extends  RuntimeException{

    public UnauthorizedExceptionHandler(String message) {
        super(message);
    }
}
