package com.ruslan.springsecurity.dto.validation;

public record ErrorResponse (
        Integer status,
        String message
) {}
