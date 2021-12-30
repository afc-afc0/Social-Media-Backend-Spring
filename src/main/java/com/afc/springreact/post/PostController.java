package com.afc.springreact.post;

import javax.validation.Valid;

import com.afc.springreact.post.dto.PostDTO;
import com.afc.springreact.shared.CurrentUser;
import com.afc.springreact.shared.GenericResponse;
import com.afc.springreact.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0")
public class PostController {
    
    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    GenericResponse savePost(@Valid @RequestBody Post post, @CurrentUser User user) {
        postService.save(post, user);
        return new GenericResponse("Post saved");
    }

    @GetMapping("/posts")
    Page<PostDTO> getPosts(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
        return postService.getPosts(page).map(PostDTO::new);
    }

    @CrossOrigin
    @GetMapping("/users/{username}/posts")
    Page<PostDTO> getUserPosts(@PathVariable String username , @PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
        return postService.getPostsOfUser(username, page).map(PostDTO::new);
    }
}
