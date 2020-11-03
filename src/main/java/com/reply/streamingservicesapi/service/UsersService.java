package com.reply.streamingservicesapi.service;

import com.reply.streamingservicesapi.entities.Payment;
import com.reply.streamingservicesapi.entities.User;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

public interface UsersService {
    boolean isUsernameExists(User user) throws InvalidUsernameException;
    boolean isOver18(LocalDate dateOfBirth) throws InvalidAgeException;
    boolean isCardRegistered(Payment payment) throws  InvalidCardException;
    boolean isValidCardNumber(String cardNumber) throws InvalidCardException;
    User registerUser(User user);
    List<User> getAllUsers();
    List<User> getAllUsersWithCreditCard();
    List<User> getAllUsersWithoutCreditCard();
    void processPayment(Payment payment);

}

