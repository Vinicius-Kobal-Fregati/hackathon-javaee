package com.stefanini.exceptions;

public class NaoVazioException extends BadRequestException{
    public NaoVazioException() {
        super();
    }

    public NaoVazioException(String message) {
        super(message);
    }
}
