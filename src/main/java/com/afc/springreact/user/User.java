package com.afc.springreact.user;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
    
    @NotNull
    @Size(min = 4 , max = 100)
    private String username;

    @NotNull
    @NotBlank
    private String displayName;
    
    @NotNull
    @Size(min = 8, max = 100)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")
    private String password;    
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
