package com.afc.springreact.user.service;

import com.afc.springreact.user.UserDTO;

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

    public void save(UserDTO userDTO){
        
        //Validate user data

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setDisplayName(userDTO.getDisplayName());
        user.setPassword(this.encoder.encode(userDTO.getPassword()));

        userRepository.save(user);
    }
}
