package com.stefanini.exceptions;

public class WrongEmailException extends BadRequestException{
    public WrongEmailException() {
        super();
    }

    public WrongEmailException(String message) {
        super(message);
    }
}
