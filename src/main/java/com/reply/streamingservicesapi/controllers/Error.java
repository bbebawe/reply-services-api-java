package com.reply.streamingservicesapi.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private LocalDateTime timestamp = LocalDateTime.now();
    List<String> errors;

    public Error (List<String> validationErrors) {
        this.errors = validationErrors;
    }
}
