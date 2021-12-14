package com.afc.springreact.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;        
        this.encoder = encoder;
    }

    public void save(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Page<User> getUsers(Pageable page) {
        return userRepository.findAll(page); 
    }
}
