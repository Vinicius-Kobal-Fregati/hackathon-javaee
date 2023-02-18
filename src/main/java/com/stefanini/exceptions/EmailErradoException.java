package com.stefanini.exceptions;

public class EmailErradoException extends BadRequestException{
    public EmailErradoException() {
        super();
    }

    public EmailErradoException(String message) {
        super(message);
    }
}
