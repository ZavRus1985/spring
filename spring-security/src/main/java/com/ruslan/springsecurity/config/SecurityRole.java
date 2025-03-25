package com.ruslan.springsecurity.config;

import com.ruslan.springsecurity.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class SecurityRole implements GrantedAuthority {

    private final Role role;

    @Override
    public String getAuthority() {
        return "ROLE_" + role.getName().toUpperCase();
    }
}
