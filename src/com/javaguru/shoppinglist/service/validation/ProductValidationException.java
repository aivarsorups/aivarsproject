package com.javaguru.shoppinglist.service.validation;

class ProductValidationException extends RuntimeException{
    public ProductValidationException(String message){
        super(message);
    }
}
