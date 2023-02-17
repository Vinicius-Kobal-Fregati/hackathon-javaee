package com.stefanini.exceptions;

public class WrongLengthException extends BadRequestException{
    public WrongLengthException() {
        super();
    }

    public WrongLengthException(String message) {
        super(message);
    }
}
