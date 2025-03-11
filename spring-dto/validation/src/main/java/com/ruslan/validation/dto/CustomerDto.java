package com.ruslan.validation.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @NotNull(message = "'name' cannot be null")
    @NotBlank(message = "'name' cannot be empty or blank")
    private String name;

    @NotNull(message = "'email' cannot be null")
    @Email(message = "wrong email format")
    private String email;

    @NotBlank(message = "'password' cannot be empty or blank")
    @Size(min = 6, max = 10, message = "Password should be from 6 to 10 symbols")
    private String password;

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
