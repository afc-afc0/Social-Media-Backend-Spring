package com.afc.springreact.post;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import com.afc.springreact.file.FileAttachment;
import com.afc.springreact.file.FileAttachmentRepository;
import com.afc.springreact.post.dto.PostSubmitDTO;
import com.afc.springreact.user.User;
import com.afc.springreact.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    PostRepository postRepository;
    UserService userService;
    FileAttachmentRepository fileAttachmentRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, FileAttachmentRepository fileAttachmentRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.fileAttachmentRepository = fileAttachmentRepository;
    }

    public void save(@Valid PostSubmitDTO postSubmitDTO , User user) {
        Post post = new Post();
        post.setContent(postSubmitDTO.getContent());
        post.setTimestamp(new Date());
        post.setUser(user);
        postRepository.save(post);
        
        Optional<FileAttachment> optionalFileAttachment = fileAttachmentRepository.findById(postSubmitDTO.getAttachmentId());
        if (optionalFileAttachment.isPresent()) {
            FileAttachment fileAttachment = optionalFileAttachment.get();
            fileAttachment.setPost(post);
            fileAttachmentRepository.save(fileAttachment);
        }
    }

    public Page<Post> getPosts(Pageable page) {
        return postRepository.findAll(page);
    }

    public Page<Post> getPostsOfUser(String username, Pageable page) {
        User user = userService.getByUsername(username);
        return postRepository.findByUser(user, page);
    }

    public Page<Post> getOldPosts(long id, String username, Pageable page) {
        Specification<Post> spec = idLessThan(id);
        if (username != null) {
            User user = userService.getByUsername(username);
            spec = spec.and(userIs(user));
        }
        return postRepository.findAll(spec, page);
    }

    public long getNewPostsCount(long id, String username) {
        Specification<Post> spec = idGreateThan(id);
        if (username != null) {
            User user = userService.getByUsername(username);
            spec = spec.and(userIs(user));    
        }
        return postRepository.count(spec);
    }

    public List<Post> getNewPosts(long id, String username, Sort sort) {
        Specification<Post> spec = idGreateThan(id);
        if (username != null) {
            User user = userService.getByUsername(username);
            spec = spec.and(userIs(user));    
        }
        return postRepository.findAll(spec, sort);
    }  

    Specification<Post> idLessThan(long id) {
        return new Specification<Post>() {

            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThan(root.get("id"), id);
            }
        };
    }

    Specification<Post> userIs(User user) {
        return new Specification<Post>() {

            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("user"), user);
            }
        };
    }

    Specification<Post> idGreateThan(long id) {
        return new Specification<Post>() {

            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("id"), id);
            }
        };
    }
}
