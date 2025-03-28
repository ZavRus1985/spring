package com.ruslan.springsecurity.service;

import com.ruslan.springsecurity.dto.UserDto;
import com.ruslan.springsecurity.entity.ApplicationUser;
import com.ruslan.springsecurity.entity.Role;
import com.ruslan.springsecurity.repository.ApplicationUserRepository;
import com.ruslan.springsecurity.repository.RoleRepository;
import com.ruslan.springsecurity.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ApplicationUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void createUser(UserDto userDto) {

        List<Role> roles = roleRepository.findByNames(userDto.roles());
        if (roles.size() != userDto.roles().size()) {
            throw new RuntimeException("Some roles where not found");
        }

        ApplicationUser user = userMapper.toEntity(userDto);
        if (userDto.password() != null) {
            user.setPassword(passwordEncoder.encode(userDto.password()));
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional
    public ApplicationUser updatePassword(String username, String newPassword) {
        ApplicationUser user = userRepository.findByUsername(username)
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

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().remove(role);
        return userRepository.save(user);
    }

    @Transactional
    public void blockUser(String username) {
        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        user.setEnabled(false); // Делаем пользователя неактивным
        userRepository.save(user);
    }

    public String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ((UserDetails) principal).getUsername().toString();
    }
}

