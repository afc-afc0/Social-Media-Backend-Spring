package com.afc.springreact.post;

import com.afc.springreact.user.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
    Page<Post> findByUser(User user, Pageable page);
    Page<Post> findByIdLessThan(long id, Pageable page);
    Page<Post> findByIdLessThanAndUser(long id, User user, Pageable page);
    long countByIdGreaterThan(long id);
}
