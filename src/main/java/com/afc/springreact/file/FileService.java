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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    
    
    AppConfiguration appConfiguration;

    @Autowired
    FileService(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
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
}
