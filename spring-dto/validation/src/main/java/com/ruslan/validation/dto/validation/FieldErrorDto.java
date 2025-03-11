package com.ruslan.validation.dto.validation;

public record FieldErrorDto(
        String field,
        String error
) {}
