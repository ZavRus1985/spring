package com.ruslan.springsecurity.controller;

import com.ruslan.springsecurity.dto.UserDto;
import com.ruslan.springsecurity.entity.ApplicationUser;
import com.ruslan.springsecurity.entity.Role;
import com.ruslan.springsecurity.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public List<ApplicationUser> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PostMapping("/users")
    public void createUser(@RequestBody UserDto user) {
        adminService.createUser(user);
    }

    @PutMapping("/users/{username}/password")
    public ApplicationUser updatePassword(@PathVariable String username, @RequestBody String newPassword) {
        return adminService.updatePassword(username, newPassword);
    }

    @PostMapping("/roles")
    public Role createRole(@RequestBody Role role) {
        return adminService.createRole(role);
    }

    @PutMapping("/users/{username}/roles/{roleName}")
    public ApplicationUser addRoleToUser(@PathVariable String username, @PathVariable String roleName) {
        return adminService.addRoleToUser(username, roleName);
    }

    @DeleteMapping("/users/{username}/roles/{roleName}")
    public ApplicationUser removeRoleFromUser(@PathVariable String username, @PathVariable String roleName) {
        return adminService.removeRoleFromUser(username, roleName);
    }


    @PutMapping("/users/{username}/block")
    public String blockUser(@PathVariable String username) {
        adminService.blockUser(username);
        return "User " + username + " has been blocked.";
    }

    @GetMapping("/current-user")
    public String getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return "Current user: " + userDetails.getUsername();
    }
}

