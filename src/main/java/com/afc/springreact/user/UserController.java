package com.afc.springreact.user;

import com.afc.springreact.shared.CurrentUser;
import com.afc.springreact.shared.GenericResponse;
import com.afc.springreact.user.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired 
    UserService userService;

    @CrossOrigin  
    @PostMapping("/api/1.0/users")   
    @ResponseStatus(HttpStatus.CREATED) // Return 201 Created Response
    public GenericResponse createUser(@RequestBody @Validated User userDTO){
        userService.save(userDTO);
        return new GenericResponse("User created succesfully");
    }

    @CrossOrigin
    @GetMapping("/api/1.0/users")
    Page<UserDTO> getUsers(Pageable page, @CurrentUser User user) {
        return userService.getUsers(page, user).map(UserDTO::new);
    }

}
