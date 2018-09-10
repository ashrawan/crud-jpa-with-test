package com.example.demo1.service;

import com.example.demo1.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    boolean updateUser(User user, Long id);

    boolean deleteUser(Long id);
}
