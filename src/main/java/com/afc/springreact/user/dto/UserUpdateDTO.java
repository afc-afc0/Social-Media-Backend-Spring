package com.afc.springreact.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.afc.springreact.shared.FileType;

import lombok.Data;

@Data
public class UserUpdateDTO {
    
    @NotNull
    @Size(min = 4 , max = 254)
    private String displayName;

    @FileType(types = {"jpeg", "png"})
    private String image;
}
