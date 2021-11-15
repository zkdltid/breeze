package com.zkdl.breeze.exception;

public class ProductIsExistInCartException extends RuntimeException {

    public ProductIsExistInCartException(String message) {
        super(message);
    }
}
