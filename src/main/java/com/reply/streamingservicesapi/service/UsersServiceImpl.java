package com.reply.streamingservicesapi.service;

import com.reply.streamingservicesapi.entities.Payment;
import com.reply.streamingservicesapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    private Map<String, User> usersList;
    private List<Payment> payments;

    @Autowired
    public UsersServiceImpl(Map<String, User> usersList) {
        this.usersList = usersList;
    }


    @Override
    public boolean isUsernameExists(User user) throws InvalidUsernameException {
        if (usersList.get(user.getUsername()) != null) {
            throw new InvalidUsernameException("username already exists");
        }
        return false;
    }

    @Override
    public boolean isOver18(LocalDate dateOfBirth) {
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
            throw new InvalidAgeException("you must be 18 or over to use the service");
        }
        return true;
    }

    @Override
    public User registerUser(User user) {
        return usersList.put(user.getUsername(), user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersList.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsersWithCreditCard() {
        return usersList.values().stream().filter(user -> user.getCreditCardNumber().length() != 0).collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsersWithoutCreditCard() {
        return usersList.values().stream().filter(user -> user.getCreditCardNumber().length() == 0).collect(Collectors.toList());
    }

    @Override
    public boolean isCardRegistered(Payment payment) throws InvalidCardException {
        boolean registeredCard = false;
        List<User> registeredUsers = usersList.values().stream().filter(user -> user.getCreditCardNumber().equals(payment.getCardNumber())).collect(Collectors.toList());
        if (registeredUsers.isEmpty()) {
            throw new InvalidCardException("card is not registered");
        }
        return true;
    }

    @Override
    public boolean isValidCardNumber(String cardNumber) throws InvalidCardException {
        if (cardNumber.length() != 0 && cardNumber.length() != 16) {
            throw new InvalidCardException("invalid card number");
        }
        return true;
    }

    @Override
    public void processPayment(Payment payment) {
        payments.add(payment);
    }
}
