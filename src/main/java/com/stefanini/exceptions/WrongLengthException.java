package com.stefanini.exceptions;

public class WrongLengthException extends Exception{
    public WrongLengthException() {
        super();
    }

    public WrongLengthException(String message) {
        super(message);
    }
}
