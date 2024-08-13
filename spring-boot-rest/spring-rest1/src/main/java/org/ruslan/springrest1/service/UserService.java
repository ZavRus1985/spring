package org.ruslan.springrest1.service;


import lombok.RequiredArgsConstructor;
import org.ruslan.springrest1.entity.User;
import org.ruslan.springrest1.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user, Integer id) {
        User updatebableUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        updatebableUser.setName(user.getName());
        updatebableUser.setPassword(user.getPassword());
        userRepository.save(updatebableUser);
    }

    @Transactional
    public void deleteUser(Integer id) {
        int deleted = userRepository.deleteUserById(id);

        if(deleted == 0) {
            throw new NoSuchElementException("User not found");
        }
    }
}
