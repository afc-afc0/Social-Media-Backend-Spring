package com.afc.springreact.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String>{
    
}
