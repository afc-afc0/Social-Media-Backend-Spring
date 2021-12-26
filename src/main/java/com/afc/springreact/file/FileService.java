package com.afc.springreact.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class FileService {
    
    public String writeBase64EncodedStringToFile(String image) throws IOException {
        String fileName = generateRandonName();
        File target = new File("pictures/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);
        
        byte[] base64Encoded = Base64.getDecoder().decode(image);
        
        outputStream.write(base64Encoded);
        outputStream.close();

        return fileName;
    }

    public String generateRandonName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
