package com.afc.springreact.user;

import java.util.*;

import com.afc.springreact.error.ApiError;
import com.afc.springreact.shared.GenericResponse;
import com.afc.springreact.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public GenericResponse createUser(@RequestBody @Validated UserDTO userDTO){
        userService.save(userDTO);
        return new GenericResponse("User created succesfully");
    }

    @CrossOrigin
    @GetMapping("/api/1.0/users")
    public String getUser(){
        return "Hello World";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception){
        ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
        
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors())
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        
        error.setValidationErrors(validationErrors);
        return error;
    }    
}
