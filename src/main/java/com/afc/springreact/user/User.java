package com.afc.springreact.user;


import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.afc.springreact.auth.Token;
import com.afc.springreact.post.Post;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "{hoaxify.constraint.username.NotNull.message}")
    @Size(min = 4 , max = 254)
    @UniqueUsername 
    private String username;

    @NotNull
    @Size(min = 4 , max = 254)
    private String displayName;
    
    @NotNull
    @Size(min = 8, max = 254)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message = "{hoaxify.constraint.username.Pattern.message}")
    private String password;    

    @Lob
    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
