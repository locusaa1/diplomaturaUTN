package com.utn.diplomaturautn.exception;

public class InvalidCallException extends RuntimeException{

    public InvalidCallException (String message) {

        super(message);
    }

    public InvalidCallException (String message, Throwable throwable){

        super(message,throwable);
    }
}
