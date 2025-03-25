package com.ruslan.springsecurity.service;

import com.ruslan.springsecurity.entity.ApplicationUser;
import com.ruslan.springsecurity.entity.Role;
import com.ruslan.springsecurity.repository.ApplicationUserRepository;
import com.ruslan.springsecurity.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ApplicationUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public ApplicationUser createUser(ApplicationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public ApplicationUser updatePassword(String username, String newPassword) {
        ApplicationUser user = userRepository.findApplicationUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @Transactional
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public ApplicationUser addRoleToUser(String username, String roleName) {
        ApplicationUser user = userRepository.findApplicationUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(role);
        return userRepository.save(user);
    }

    @Transactional
    public ApplicationUser removeRoleFromUser(String username, String roleName) {
        ApplicationUser user = userRepository.findApplicationUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getRoles().removeIf(role -> role.getName().equalsIgnoreCase(roleName));
        return userRepository.save(user);
    }

    @Transactional
    public void blockUser(String username) {
        ApplicationUser user = userRepository.findApplicationUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        user.setEnabled(false); // Делаем пользователя неактивным
        userRepository.save(user);
    }
}

