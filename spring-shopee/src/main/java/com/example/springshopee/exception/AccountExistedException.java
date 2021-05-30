package com.example.springshopee.exception;

public class AccountExistedException extends Exception{
    public AccountExistedException(String message){
        super(message);
    }
}
