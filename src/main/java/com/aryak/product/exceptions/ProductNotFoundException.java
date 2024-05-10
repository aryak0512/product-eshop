package com.aryak.product.exceptions;

public class ProductNotFoundException extends RuntimeException{

    final int code;
    final String message;

    public ProductNotFoundException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
