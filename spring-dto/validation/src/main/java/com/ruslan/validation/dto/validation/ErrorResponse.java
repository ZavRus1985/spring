package com.ruslan.validation.dto.validation;

public record ErrorResponse (
        Integer status,
        String message
) {}
