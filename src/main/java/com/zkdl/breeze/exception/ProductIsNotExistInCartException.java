package com.zkdl.breeze.exception;

public class ProductIsNotExistInCartException extends RuntimeException {

    public ProductIsNotExistInCartException(String message) {
        super(message);
    }
}
