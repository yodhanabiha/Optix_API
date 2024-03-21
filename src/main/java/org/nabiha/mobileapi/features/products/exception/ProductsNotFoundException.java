package org.nabiha.mobileapi.features.products.exception;

public class ProductsNotFoundException extends RuntimeException{
    public ProductsNotFoundException(String message){
        super(message);
    }
}