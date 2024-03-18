package org.nabiha.mobileapi.features.users.exception;

public class UsersNotFoundException extends RuntimeException{
    public UsersNotFoundException(String message){
        super(message);
    }
}
