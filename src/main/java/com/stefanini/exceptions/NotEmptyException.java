package com.stefanini.exceptions;

public class NotEmptyException extends Exception{
    public NotEmptyException() {
        super();
    }

    public NotEmptyException(String message) {
        super(message);
    }
}
