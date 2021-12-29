package com.afc.springreact.post;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    PostRepository postRepository;
    
    @Autowired
    public PostService(PostRepository postRepository) {
        super();
        this.postRepository = postRepository;
    }

    public void save(Post post) {
        post.setTimestamp(new Date());
        postRepository.save(post);
    }
    
}
