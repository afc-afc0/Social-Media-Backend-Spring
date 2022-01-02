package com.afc.springreact.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.afc.springreact.configuration.AppConfiguration;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@EnableScheduling
public class FileService {
    
    AppConfiguration appConfiguration;
    Tika tika;

    FileAttachmentRepository fileAttachmentRepository;

    @Autowired
    FileService(AppConfiguration appConfiguration, FileAttachmentRepository fileAttachmentRepository) {
        this.appConfiguration = appConfiguration;
        this.tika = new Tika();
        this.fileAttachmentRepository = fileAttachmentRepository;
    }

    public String writeBase64EncodedStringToFile(String image) throws IOException {
        String fileName = generateRandonName();
        File target = new File(appConfiguration.getProfileStoragePath() + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);
        
        byte[] base64Encoded = Base64.getDecoder().decode(image);
        
        outputStream.write(base64Encoded);
        outputStream.close();

        return fileName;
    }

    public String generateRandonName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleleteProfileImageFile(String fileName) {
        if (fileName == null) {
            return;
        }
        deleteFile(Paths.get(appConfiguration.getProfileStoragePath(), fileName));
    }

    public void deleteAttachmentFile(String fileName) {
        if (fileName == null) {
            return;
        }
        deleteFile(Paths.get(appConfiguration.getAttachmentStoragePath(), fileName));
    }

    private void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String detectType(String base64) {
        byte[] base64Encoded = Base64.getDecoder().decode(base64);
        return detectType(base64Encoded);
    }

    public String detectType(byte[] arr) {
        return tika.detect(arr);
    }

    public FileAttachment savePostAttachments(MultipartFile multipartFile) {
        String fileName = generateRandonName();
        File target = new File(appConfiguration.getAttachmentStoragePath() + "/" + fileName);
        String fileType = null;        
        try {
            byte[] arr = multipartFile.getBytes();
            OutputStream outputStream = new FileOutputStream(target);
            outputStream.write(arr);
            outputStream.close();
            fileType = detectType(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileAttachment attachment = new FileAttachment();
        attachment.setName(fileName);
        attachment.setDate(new Date());
        attachment.setFileType(fileType);

        return fileAttachmentRepository.save(attachment);
    }

    final long timeAgo = 24 * 60 * 60 * 1000;//24 hours
    @Scheduled(fixedRate = timeAgo)
    public void cleanupStorage() {
        Date date = new Date(System.currentTimeMillis() - timeAgo);
        List<FileAttachment> filesToDelete =  fileAttachmentRepository.findByDateBeforeAndPostIsNull(date);

        for (FileAttachment file : filesToDelete) {
            deleteAttachmentFile(file.getName());
            fileAttachmentRepository.deleteById(file.getId());
        }
    }

    
}
