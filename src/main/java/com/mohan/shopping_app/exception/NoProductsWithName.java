package com.mohan.shopping_app.exception;

public class NoProductsWithName extends RuntimeException{

    public NoProductsWithName(String message) {
        super(message);
    }
}
