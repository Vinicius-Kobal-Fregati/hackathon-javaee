package com.stefanini.exceptions;

public class MaiorQuePermitidoException extends BadRequestException{
    public MaiorQuePermitidoException() {
        super();
    }

    public MaiorQuePermitidoException(String message) {
        super(message);
    }
}
