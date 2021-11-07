package com.afc.springreact.user;

import com.afc.springreact.shared.GenericResponse;
import com.afc.springreact.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    
    @Autowired // Dependency Injection
    UserService userService;

    @CrossOrigin  // When we send the request from Frontend because our port is different we cant pass the CORS so we add cross origin
    @PostMapping("/api/1.0/users")   
    @ResponseStatus(HttpStatus.CREATED) // Return 201 Create Response
    public GenericResponse createUser(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
        GenericResponse response = new GenericResponse("User created succesfully");
        return response;
    }

    @CrossOrigin
    @GetMapping("/api/1.0/users")
    public String getUser(){
        return "Hello World";
    }
}
