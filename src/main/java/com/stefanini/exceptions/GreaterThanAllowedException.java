package com.stefanini.exceptions;

public class GreaterThanAllowedException extends BadRequestException{
    public GreaterThanAllowedException() {
        super();
    }

    public GreaterThanAllowedException(String message) {
        super(message);
    }
}
