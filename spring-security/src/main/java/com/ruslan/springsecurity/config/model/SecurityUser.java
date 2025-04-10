package com.ruslan.springsecurity.config.model;

import com.ruslan.springsecurity.entity.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails {
    private final ApplicationUser user;

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(SecurityRole::new)
                .toList();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}