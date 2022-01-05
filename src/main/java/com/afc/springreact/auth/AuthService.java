package com.afc.springreact.auth;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.afc.springreact.user.User;
import com.afc.springreact.user.UserRepository;
import com.afc.springreact.user.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    TokenRepository tokenRepository;

    @Autowired
    AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public AuthResponse authenticate(Credentials credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if (user == null) {
            throw new AuthException();
        }
        boolean passwordMatches = passwordEncoder.matches(credentials.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new AuthException();
        }

        UserDTO userDTO = new UserDTO(user);
        String token = generateRandonToken(); 

        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUser(user);
        tokenRepository.save(tokenEntity);

        AuthResponse response = new AuthResponse();
        response.setUser(userDTO);
        response.setToken(token);
        return response;
    }

    @Transactional
    public UserDetails getUserDetails(String token) {

        Optional<Token> optionalToken = tokenRepository.findById(token);
        if (!optionalToken.isPresent()) {
            return null;
        }
        return optionalToken.get().getUser();
    }

    public String generateRandonToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void clearToken(String token) {
        tokenRepository.deleteById(token);
    }
}
