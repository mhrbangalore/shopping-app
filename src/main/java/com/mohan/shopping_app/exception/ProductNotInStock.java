package com.mohan.shopping_app.exception;

public class ProductNotInStock extends RuntimeException {
    public ProductNotInStock(String message) {
        super(message);
    }
}
