package org.nabiha.mobileapi.features.products.exception;

public class ProductsServiceBusinessException extends RuntimeException{
    public ProductsServiceBusinessException(String message){
        super(message);
    }
}
