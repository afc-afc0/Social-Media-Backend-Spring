package com.afc.springreact.post.dto;

import com.afc.springreact.file.dto.FileAttachmentDTO;
import com.afc.springreact.post.Post;
import com.afc.springreact.user.dto.UserDTO;

import lombok.Data;

@Data
public class PostDTO {

    private long id;

    private String content;

    private long timestamp;

    private UserDTO user;

    private FileAttachmentDTO fileAttachment;

    public PostDTO(Post post) {
        this.setId(post.getId());
        this.setContent(post.getContent());
        this.setTimestamp(post.getTimestamp().getTime());
        this.setUser(new UserDTO(post.getUser()));

        if (post.getFileAttachment() != null) {
            this.fileAttachment = new FileAttachmentDTO(post.getFileAttachment());
        }
    }
}
