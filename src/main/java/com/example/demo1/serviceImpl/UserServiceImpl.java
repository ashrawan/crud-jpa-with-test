package com.example.demo1.serviceImpl;

import com.example.demo1.exception.DataNotFoundException;
import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import com.example.demo1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {


        List<User> userList = userRepository.findAll();
        if (userList == null) {
            throw new DataNotFoundException("cannot find users");
        }
        return userList;
    }

    @Override
    public User getUserById(Long id) {

        if (id == null) {
            throw new DataNotFoundException("cannot find user");
        }
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new DataNotFoundException("cannot find user");
        }
        return user.get();

//        return userRepository.findById(id).orElseThrow(()-> new DataNotFoundException("cannot find user"));
    }

    @Override
    public User createUser(User user) {
        if(user == null){
            throw new DataNotFoundException("user data is empty");
        }
        User userByUsername = userRepository.findOneByUsername(user.getUsername());
        if (userByUsername != null ) {
            throw new DataNotFoundException("username already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public boolean updateUser(User user, Long id) {

        Optional<User> user1 = userRepository.findById(id);
        if (!user1.isPresent()) {
            throw new DataNotFoundException("cannot update user");
        }
        user1.get().setFullName(user.getFullName());
        user1.get().setStatus(user.getStatus());

        User updatedUser = userRepository.save(user1.get());
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {

        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new DataNotFoundException("cannot delete user");
        }
        userRepository.delete(user.get());
        return true;
    }

}
