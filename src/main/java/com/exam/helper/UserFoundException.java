package com.exam.helper;

public class UserFoundException extends RuntimeException{


    public UserFoundException() {
        super("Username Exit");
    }

    public UserFoundException(String message) {
        super(message);
    }
}
