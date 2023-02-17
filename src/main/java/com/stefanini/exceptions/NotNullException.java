package com.stefanini.exceptions;

public class NotNullException extends BadRequestException{
    public NotNullException() {
        super();
    }

    public NotNullException(String message) {
        super(message);
    }
}
