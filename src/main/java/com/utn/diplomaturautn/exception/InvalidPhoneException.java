package com.utn.diplomaturautn.exception;

public class InvalidPhoneException extends RuntimeException {

    public InvalidPhoneException(String message) {

        super(message);
    }

    public InvalidPhoneException(String message, Throwable throwable) {

        super(message, throwable);
    }
}
