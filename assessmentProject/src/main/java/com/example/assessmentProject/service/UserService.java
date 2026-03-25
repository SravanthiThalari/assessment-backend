package com.example.assessmentProject.service;

import com.example.assessmentProject.model.Users;
import com.example.assessmentProject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users saveUser(Users user){

        Users existing = userRepository.findByEmail(user.getEmail());

        if(existing != null){
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}