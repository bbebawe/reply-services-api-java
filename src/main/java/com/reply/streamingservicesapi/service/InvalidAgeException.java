package com.reply.streamingservicesapi.service;

import lombok.Data;


public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
    }

    public InvalidAgeException(String message) {
        super(message);
    }
}
