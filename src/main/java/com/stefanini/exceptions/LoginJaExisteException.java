package com.stefanini.exceptions;

public class LoginJaExisteException extends BadRequestException{
    public LoginJaExisteException() {
        super();
    }

    public LoginJaExisteException(String message) {
        super(message);
    }
}
