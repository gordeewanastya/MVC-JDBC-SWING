package com.gordeeva.TJI_Lab2.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }

}
