package com.stefanini.exceptions;

public class NotExistException extends BadRequestException{
    public NotExistException() {
        super();
    }

    public NotExistException(String message) {
        super(message);
    }
}
