package com.mohan.shopping_app.exception;

public class ProductAlreadyExists extends RuntimeException{

    public ProductAlreadyExists(String message) {
        super(message);
    }

}
