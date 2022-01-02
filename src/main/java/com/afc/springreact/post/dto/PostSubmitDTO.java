package com.afc.springreact.post.dto;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostSubmitDTO {
    
    @Size(min = 1, max = 1000)
    private String content;

    private long attachmentId;
}
