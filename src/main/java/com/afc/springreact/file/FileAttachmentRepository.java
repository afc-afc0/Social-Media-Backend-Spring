package com.afc.springreact.file;

import java.util.Date;
import java.util.List;

import com.afc.springreact.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long>{
    List<FileAttachment> findByDateBeforeAndPostIsNull(Date date);

    List<FileAttachment> findByPostUser(User user);
}
