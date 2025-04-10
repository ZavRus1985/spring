package com.ruslan.springsecurity.config.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public record UserInfo(
        String username,
        Set<? extends GrantedAuthority> authorities
) { }
