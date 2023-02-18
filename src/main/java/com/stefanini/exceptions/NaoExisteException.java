package com.stefanini.exceptions;

public class NaoExisteException extends BadRequestException{
    public NaoExisteException() {
        super();
    }

    public NaoExisteException(String message) {
        super(message);
    }
}
