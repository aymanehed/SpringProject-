package com.example.Springboot_project.Services;

import com.example.Springboot_project.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    User gerUser(Long id);
    List<User> getAllUser();
    void DeleteUserById(Long id);
    void DeleteAllUsers();


}
