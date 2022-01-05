package com.afc.springreact.auth;

import javax.transaction.Transactional;

import com.afc.springreact.user.User;
import com.afc.springreact.user.UserRepository;
import com.afc.springreact.user.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        String token = Jwts.builder().setSubject("" + user.getId()).signWith(SignatureAlgorithm.HS512, "my-app-secret").compact();
        AuthResponse response = new AuthResponse();
        response.setUser(userDTO);
        response.setToken(token);
        return response;
    }

    @Transactional
    public UserDetails getUserDetails(String token) {
        JwtParser parser = Jwts.parser().setSigningKey("my-app-secret");
        
        try {
            parser.parse(token);
            Claims claims = parser.parseClaimsJws(token).getBody();
            long userId = Long.parseLong(claims.getSubject());
            User user = userRepository.getById(userId);
            User actualUser = (User)((HibernateProxy)user).getHibernateLazyInitializer().getImplementation();

            return actualUser;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
