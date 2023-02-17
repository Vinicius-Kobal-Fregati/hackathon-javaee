package com.stefanini.exceptions;

public class WrongEmailException extends Exception{
    public WrongEmailException() {
        super();
    }

    public WrongEmailException(String message) {
        super(message);
    }
}
