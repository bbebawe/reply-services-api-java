package com.reply.streamingservicesapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @NotNull
    @Size(min = 16, message = "invalid card number")
    private String cardNumber;
    @NotNull
    private BigDecimal amount;
}
