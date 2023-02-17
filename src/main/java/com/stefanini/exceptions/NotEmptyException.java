package com.stefanini.exceptions;

public class NotEmptyException extends BadRequestException{
    public NotEmptyException() {
        super();
    }

    public NotEmptyException(String message) {
        super(message);
    }
}
