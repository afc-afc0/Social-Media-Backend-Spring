package com.afc.springreact.auth;

import java.util.HashMap;
import java.util.Map;

import com.afc.springreact.shared.CurrentUser;
import com.afc.springreact.user.User;
import com.afc.springreact.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    
    @Autowired
    UserRepository userRepository;
        
    @CrossOrigin
    @PostMapping("/api/1.0/auth")
    ResponseEntity<?> handleAuthentication(@CurrentUser User user) {
        System.out.println("AUTHENTICATION >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map<String, String> responseBody = createResponseBody(user);
        return ResponseEntity.ok(responseBody);
    }

    private Map<String, String> createResponseBody(User user){
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("username", user.getUsername());
        responseBody.put("displayName", user.getDisplayName());
        responseBody.put("image", user.getImage());

        return responseBody;
    }
}
