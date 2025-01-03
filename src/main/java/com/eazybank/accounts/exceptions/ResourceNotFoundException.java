package com.eazybank.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
        // Super() in Exception or RunTimeException only accepts String value
        super(String.format("%s not found with given input data %s: '%s'", resourceName,fieldName,fieldValue));
    }
}
