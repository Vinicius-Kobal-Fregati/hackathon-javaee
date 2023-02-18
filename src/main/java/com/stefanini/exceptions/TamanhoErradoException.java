package com.stefanini.exceptions;

public class TamanhoErradoException extends BadRequestException{
    public TamanhoErradoException() {
        super();
    }

    public TamanhoErradoException(String message) {
        super(message);
    }
}
