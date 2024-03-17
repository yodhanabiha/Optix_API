package org.nabiha.mobileapi.users.exception;

public class UsersNotFoundException extends RuntimeException{
    public UsersNotFoundException(String message){
        super(message);
    }
}
