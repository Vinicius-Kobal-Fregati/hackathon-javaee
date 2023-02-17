package com.stefanini.exceptions;

public class ObjectNotFoundException extends BadRequestException{
    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
