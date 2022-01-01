package com.afc.springreact.file;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/api/1.0/post-attachments")
    Map<String, String> savePostAttachment(@RequestParam MultipartFile file) {
        String fileName = fileService.savePostAttachments(file);
        return Collections.singletonMap("name", fileName);
    }
}
