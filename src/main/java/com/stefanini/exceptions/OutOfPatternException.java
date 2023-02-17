package com.stefanini.exceptions;

public class OutOfPatternException extends BadRequestException{
    public OutOfPatternException() {
        super();
    }

    public OutOfPatternException(String message) {
        super(message);
    }
}
