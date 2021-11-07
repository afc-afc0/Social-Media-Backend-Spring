package com.afc.springreact.user;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String displayName;
    private String password;
    private String passwordRepeat;
    
    // Lombok handles everything for us
    // public String getUsername() {
    //     return username;
    // }
    // public void setUsername(String username) {
    //     this.username = username;
    // }
    // public String getDisplayName() {
    //     return displayName;
    // }
    // public void setDisplayName(String displayName) {
    //     this.displayName = displayName;
    // }
    // public String getPassword() {
    //     return password;
    // }
    // public void setPassword(String password) {
    //     this.password = password;
    // }
    // public String getPasswordRepeat() {
    //     return passwordRepeat;
    // }
    // public void setPasswordRepeat(String passwordRepeat) {
    //     this.passwordRepeat = passwordRepeat;
    // }
}
