package com.utn.diplomaturautn.exception;

public class ErrorSavingEntityException extends RuntimeException {

    public ErrorSavingEntityException(String message) {

        super(message);
    }

    public ErrorSavingEntityException(String message, Throwable throwable) {

        super(message, throwable);
    }
}
