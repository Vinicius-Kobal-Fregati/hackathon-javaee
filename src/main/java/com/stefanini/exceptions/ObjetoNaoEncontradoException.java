package com.stefanini.exceptions;

public class ObjetoNaoEncontradoException extends BadRequestException{
    public ObjetoNaoEncontradoException() {
        super();
    }

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
