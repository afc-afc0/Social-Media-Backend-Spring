package com.afc.springreact.file.dto;

import com.afc.springreact.file.FileAttachment;

import lombok.Data;

@Data
public class FileAttachmentDTO {
    String name;

    public FileAttachmentDTO(FileAttachment fileAttachment) {
        this.setName(fileAttachment.getName());
    }
}
