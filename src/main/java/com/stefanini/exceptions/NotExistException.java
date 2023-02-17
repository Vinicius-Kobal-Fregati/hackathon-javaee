package com.stefanini.exceptions;

public class NotExistException extends Exception{
    public NotExistException() {
        super();
    }

    public NotExistException(String message) {
        super(message);
    }
}
