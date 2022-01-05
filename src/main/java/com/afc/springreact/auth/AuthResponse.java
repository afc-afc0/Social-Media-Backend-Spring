package com.afc.springreact.auth;

import com.afc.springreact.user.dto.UserDTO;
import lombok.Data;

@Data
public class AuthResponse {

    private String token;

    private UserDTO user;

}
