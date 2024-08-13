package org.ruslan.springrest1.controller;

import lombok.RequiredArgsConstructor;
import org.ruslan.springrest1.entity.User;
import org.ruslan.springrest1.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users", produces = "application/xml")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public User getEmployeeById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    //-------------------------------------

    @PostMapping("/save")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    //------------------------------------

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(user, id);
    }

    //-------------------------------------

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
