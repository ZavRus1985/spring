package com.ruslan.validation.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class BankCardDto {

    @NotNull(message = "'bankName' cannot be null")
    @NotBlank(message = "'bankName' cannot be empty or blank")
    private String bankName;

    @Positive(message = "bankCardId should be positive")
    private Integer numberCard;

    @Size(min = 3, max = 3, message = "cvcCode should be 3 symbols")
    private Integer cvcCode;

    @PositiveOrZero(message = "balance should be positive or zero")
    private BigDecimal balance;
}
