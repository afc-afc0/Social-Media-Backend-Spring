package com.afc.springreact.auth;

import com.afc.springreact.shared.CurrentUser;
import com.afc.springreact.user.User;
import com.afc.springreact.user.UserRepository;
import com.afc.springreact.user.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    
    @Autowired
    UserRepository userRepository;
        
    @CrossOrigin
    @PostMapping("/api/1.0/auth")
    UserDTO handleAuthentication(@CurrentUser User user) {
        return new UserDTO(user);
    }  
}
