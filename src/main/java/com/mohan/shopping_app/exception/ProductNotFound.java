package com.mohan.shopping_app.exception;

public class ProductNotFound extends RuntimeException{

    public ProductNotFound(String message) {
        super(message);
    }
}
