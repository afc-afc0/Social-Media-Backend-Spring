package com.afc.springreact.file.dto;

import com.afc.springreact.file.FileAttachment;

import lombok.Data;

@Data
public class FileAttachmentDTO {
    String name;
    String fileType;

    public FileAttachmentDTO(FileAttachment fileAttachment) {
        this.setName(fileAttachment.getName());
        this.fileType = fileAttachment.getFileType();
    }
}
