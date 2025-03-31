package com.leogouchon.squashapp.service;

import com.leogouchon.squashapp.model.Users;
import com.leogouchon.squashapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users createUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setIsAdmin(false);
        return userRepository.save(users);
    }

    public Users getUserByEmail(String username) {
        return userRepository.findByEmail(username).orElse(null);
    }

    protected Users getUserByToken(String token) {
        return userRepository.findByToken(token).orElse(null);
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users updateUser(Users users) {
        Users existingUsers = getUserById(users.getId());
        existingUsers.setEmail(users.getEmail());
        existingUsers.setPassword(users.getPassword());
        return userRepository.save(existingUsers);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public List<Users> getUsersWithLinkedPlayer() {
        return userRepository.findByPlayerIsNotNull();
    }

    public void updateTokenUser(Users user) {
        Users existingUsers = getUserById(user.getId());
        existingUsers.setToken(user.getToken());
        userRepository.save(existingUsers);
    }

}
