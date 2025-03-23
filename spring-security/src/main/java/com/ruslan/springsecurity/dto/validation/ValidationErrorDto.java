package com.ruslan.springsecurity.dto.validation;

import java.util.List;

public record ValidationErrorDto(
        Integer status,
        List<FieldErrorDto> errors
) {}
