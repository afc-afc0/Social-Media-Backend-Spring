package com.afc.springreact.post;

import java.util.List;

import com.afc.springreact.user.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
    Page<Post> findByUser(User user, Pageable page);
    Page<Post> findByIdLessThan(long id, Pageable page);
    Page<Post> findByIdLessThanAndUser(long id, User user, Pageable page);
    long countByIdGreaterThan(long id);
    long countByIdGreaterThanAndUser(long id, User user);
    List<Post> findByIdGreaterThan(long id, Sort sort);
    List<Post> findByIdGreaterThanAndUser(long id, User username, Sort sort);
}
