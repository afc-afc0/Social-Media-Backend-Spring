package com.afc.springreact.user;

import java.io.IOException;

import com.afc.springreact.error.NotFoundException;
import com.afc.springreact.file.FileService;
import com.afc.springreact.user.dto.UserUpdateDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    
    PasswordEncoder encoder;

    FileService fileService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder, FileService fileService){
        this.userRepository = userRepository;        
        this.encoder = encoder;
        this.fileService = fileService;
    }

    public void save(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Page<User> getUsers(Pageable page, User user) {
        if (user != null) {
            return userRepository.findByUsernameNot(user.getUsername(), page);
        }
        return userRepository.findAll(page); 
    }

    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    public User updateUser(String username, UserUpdateDTO updatedUser) {
        User inDB = getByUsername(username);
        inDB.setDisplayName(updatedUser.getDisplayName());
        if(updatedUser.getImage() != null) {
            String oldImageName = inDB.getImage();
            try {
                String storedFileName = fileService.writeBase64EncodedStringToFile(updatedUser.getImage());
                inDB.setImage(storedFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileService.deleleteProfileImageFile(oldImageName);
        }
        return userRepository.save(inDB);
    }    
}
