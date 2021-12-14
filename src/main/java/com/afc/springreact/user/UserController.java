package com.afc.springreact.user;

import java.util.List;

import com.afc.springreact.shared.GenericResponse;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired 
    UserService userService;

    @CrossOrigin  
    @PostMapping("/api/1.0/users")   
    @ResponseStatus(HttpStatus.CREATED) // Return 201 Create Response
    public GenericResponse createUser(@RequestBody @Validated User userDTO){
        userService.save(userDTO);
        return new GenericResponse("User created succesfully");
    }

    @CrossOrigin
    @GetMapping("/api/1.0/users")
    @JsonView(Views.Public.class)
    Page<User> getUsers(Pageable page) {
        return userService.getUsers(page);
    }

}
