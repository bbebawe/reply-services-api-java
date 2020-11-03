package com.reply.streamingservicesapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotBlank(message = "username can not be empty")
    @Pattern(regexp="^[A-Za-z0-9]+$",message="username must contain only alphanumeric characters without spaces")
    private String username;
    @NotBlank(message = "password can not be empty")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[A-Z])[a-zA-Z0-9]{8,}$",message="password must be 8 characters or more with at least one upper case letter & number")
    private String password;
    @NotBlank(message = "email address can not be empty")
    @Email(message = "invalid email address")
    private String email;
    @NotNull(message = "date of birth can not be empty")
    @Past(message = "data of birth can not be in future")
    private LocalDate dateOfBirth;
    private String creditCardNumber;
}
