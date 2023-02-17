package com.stefanini.exceptions;

public class NotNullException extends Exception{
    public NotNullException() {
        super();
    }

    public NotNullException(String message) {
        super(message);
    }
}
