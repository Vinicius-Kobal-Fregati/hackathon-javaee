package com.stefanini.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
