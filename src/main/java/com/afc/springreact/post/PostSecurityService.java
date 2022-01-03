package com.afc.springreact.post;

import java.util.Optional;

import com.afc.springreact.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "postSecurity")
public class PostSecurityService {

    PostRepository postRepository;

    @Autowired
    public PostSecurityService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public boolean isAllowedToDelete(long id, User user) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) {
            return false;
        }

        Post post = optionalPost.get();
        if (post.getUser().getId() != user.getId()) {
            return false;
        }

        return true;
    }
}
