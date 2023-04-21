package com.example.Springboot_project.Services;

import com.example.Springboot_project.Entities.User;
import com.example.Springboot_project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
@Autowired
    UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User gerUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void DeleteUserById(Long id) {
userRepository.deleteById(id);
    }

    @Override
    public void DeleteAllUsers() {
userRepository.deleteAll();
    }
}
