package com.afc.springreact.user.service;

import javax.validation.Valid;

import com.afc.springreact.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    UserRepository userRepository;

    PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;        
        this.encoder = new BCryptPasswordEncoder();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
