package com.afc.springreact.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import com.afc.springreact.configuration.AppConfiguration;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    
    AppConfiguration appConfiguration;
    Tika tika;

    @Autowired
    FileService(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
        this.tika = new Tika();
    }

    public String writeBase64EncodedStringToFile(String image) throws IOException {
        String fileName = generateRandonName();
        File target = new File(appConfiguration.getUploadPath() + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);
        
        byte[] base64Encoded = Base64.getDecoder().decode(image);
        
        outputStream.write(base64Encoded);
        outputStream.close();

        return fileName;
    }

    public String generateRandonName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleleteFile(String fileName) {
        if (fileName == null) {
            return;
        }
        try {
            Files.deleteIfExists(Paths.get(appConfiguration.getUploadPath(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String detectType(String image) {
        byte[] base64Encoded = Base64.getDecoder().decode(image);
        return tika.detect(base64Encoded);
    }

    public String savePostAttachments(MultipartFile multipartFile) {
        String fileName = generateRandonName();
        File target = new File(appConfiguration.getUploadPath() + "/" + fileName);
                
        try {
            OutputStream outputStream = new FileOutputStream(target);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
