package com.stefanini.exceptions;

public class ForaDoPadraoException extends BadRequestException{
    public ForaDoPadraoException() {
        super();
    }

    public ForaDoPadraoException(String message) {
        super(message);
    }
}
