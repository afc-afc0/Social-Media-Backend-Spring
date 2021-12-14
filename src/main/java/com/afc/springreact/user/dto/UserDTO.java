package com.afc.springreact.user.dto;

import com.afc.springreact.user.User;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String displayName;
    private String image;

    public UserDTO(User user) {
        this.setUsername(user.getUsername());
        this.setDisplayName(user.getDisplayName());
        this.setImage(user.getImage());
    }
}
