package com.ruslan.springsecurity.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankCardDto {

    @NotNull(message = "'bankName' cannot be null")
    @NotBlank(message = "'bankName' cannot be empty or blank")
    private String bankName;

    @NotNull(message = "'numberCard' cannot be null")
    @Positive(message = "numberCard should be positive")
    private Integer numberCard;

    @Min(value = 000, message = "cvcCode must be between 000 and 999")
    @Max(value = 999, message = "cvcCode must be between 000 and 999")
    private Integer cvcCode;

    @PositiveOrZero(message = "balance should be positive or zero")
    private BigDecimal balance;
}
