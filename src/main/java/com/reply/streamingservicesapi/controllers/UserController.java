package com.reply.streamingservicesapi.controllers;

import com.reply.streamingservicesapi.entities.Payment;
import com.reply.streamingservicesapi.entities.User;
import com.reply.streamingservicesapi.service.InvalidAgeException;
import com.reply.streamingservicesapi.service.InvalidCardException;
import com.reply.streamingservicesapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    private UsersService service;

    @Autowired
    public UserController(UsersService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) throws InvalidAgeException, InvalidCardException {
        service.isUsernameExists(user);
        service.isOver18(user.getDateOfBirth());
        service.isValidCardNumber(user.getCreditCardNumber());
        service.registerUser(user);
        return ResponseEntity.ok("user created");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(name = "card", required = false) String creditCard) {
        if (creditCard != null && creditCard.equals("yes")) {
            return ResponseEntity.ok(service.getAllUsersWithCreditCard());
        } else if (creditCard != null && creditCard.equals("no")) {
            return ResponseEntity.ok(service.getAllUsersWithoutCreditCard());
        }
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping("/payments")
    public ResponseEntity processPayment(@Valid @RequestBody Payment payment) throws InvalidCardException {
        if (payment.getAmount().toString().length() != 3) {
            throw new ValidationException("invalid payment amount");
        }
        service.isCardRegistered(payment);
        service.processPayment(payment);
        return ResponseEntity.ok("payment processed");
    }

}
