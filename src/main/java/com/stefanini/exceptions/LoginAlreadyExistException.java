package com.stefanini.exceptions;

public class LoginAlreadyExistException extends BadRequestException{
    public LoginAlreadyExistException() {
        super();
    }

    public LoginAlreadyExistException(String message) {
        super(message);
    }
}
