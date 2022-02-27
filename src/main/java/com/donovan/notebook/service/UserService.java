package com.donovan.notebook.service;

import com.donovan.notebook.model.User;
import com.donovan.notebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean exist(String username) {
        return this.userRepository.existsById(username);
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findById(username);
    }

    public void addUser(User user) {
        this.userRepository.insert(user);
    }
}
