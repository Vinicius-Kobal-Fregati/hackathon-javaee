package com.stefanini.exceptions;

public class NaoNuloException extends BadRequestException{
    public NaoNuloException() {
        super();
    }

    public NaoNuloException(String message) {
        super(message);
    }
}
