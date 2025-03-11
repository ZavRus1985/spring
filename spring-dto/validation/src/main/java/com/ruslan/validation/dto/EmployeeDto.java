package com.ruslan.validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    @NotNull(message = "'name' cannot be null")
    @NotBlank(message = "'name' cannot be empty or blank")
    private String name;

    @NotNull(message = "'email' cannot be null")
    @Email(message = "wrong email format")
    private String email;

    @PositiveOrZero(message = "departmentId should be positive or zero")
    private Integer departmentId;

    @NotNull(message = "'departmentName' cannot be null")
    @NotBlank(message = "'departmentName' cannot be empty or blank")
    private String departmentName;
}
