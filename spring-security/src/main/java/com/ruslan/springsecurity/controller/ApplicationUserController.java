package com.ruslan.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
public class ApplicationUserController {

    @GetMapping("/GET")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String getHello() {
        return "GET hello";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String postStartMessage() {
        return "POST hello!!!";
    }
}
