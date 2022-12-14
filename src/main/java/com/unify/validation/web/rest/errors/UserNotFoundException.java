package com.unify.validation.web.rest.errors;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
