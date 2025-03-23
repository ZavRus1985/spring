package com.ruslan.springsecurity.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Valid
    private BankCardDto bankCard;
}
