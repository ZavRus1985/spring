package org.ruslan.springbootbasic.service;

import org.ruslan.springbootbasic.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getAllUsers() {
        return List.of(
                new User("Ivan", 22),
                new User("Bob", 33),
                new User("Anna", 44));
    }
}
