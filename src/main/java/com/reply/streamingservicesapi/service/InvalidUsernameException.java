package com.reply.streamingservicesapi.service;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException() {
    }

    public InvalidUsernameException(String message) {
        super(message);
    }
}
