package com.reply.streamingservicesapi.service;

public class InvalidCardException extends Exception {
    public InvalidCardException() {
    }

    public InvalidCardException(String message) {
        super(message);
    }
}
