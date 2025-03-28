package com.ruslan.springsecurity.config.dto;

public record AuthRequest(
        String username,
        String password
) { }
