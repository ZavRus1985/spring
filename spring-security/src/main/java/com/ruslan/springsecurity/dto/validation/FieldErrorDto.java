package com.ruslan.springsecurity.dto.validation;

public record FieldErrorDto(
        String field,
        String error
) {}
