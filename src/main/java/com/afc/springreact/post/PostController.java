package com.afc.springreact.post;

import javax.validation.Valid;

import com.afc.springreact.shared.GenericResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    
    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/1.0/posts")
    GenericResponse savePost(@Valid @RequestBody Post post) {
        postService.save(post);
        return new GenericResponse("Post saved");
    }

}
