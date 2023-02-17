package com.stefanini.exceptions;

public class GreaterThanAllowedException extends Exception{
    public GreaterThanAllowedException() {
        super();
    }

    public GreaterThanAllowedException(String message) {
        super(message);
    }
}
