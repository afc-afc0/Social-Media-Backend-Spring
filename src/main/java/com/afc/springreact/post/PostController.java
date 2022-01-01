package com.afc.springreact.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping({"/posts/{id:[0-9]+}", "/users/{username}/posts/{id:[0-9]+}"})
    ResponseEntity<?> getPostsRelative(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page,
            @PathVariable long id, 
            @PathVariable(required = false) String username,
            @RequestParam(name="count", required = false, defaultValue = "false") boolean count,
            @RequestParam(name="direction", defaultValue = "before") String direction) 
    {
        if (count) {
            long newPostCount = postService.getNewPostsCount(id, username);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newPostCount);
            return ResponseEntity.ok(response);
        }

        if (direction.equals("after")) {
            List<Post> newPosts = postService.getNewPosts(id, username, page.getSort());
            List<PostDTO> newPostsDTO = newPosts.stream().map(PostDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(newPostsDTO);
        }

        return ResponseEntity.ok(postService.getOldPosts(id, username, page).map(PostDTO::new));
    }

    @GetMapping("/users/{username}/posts")
    Page<PostDTO> getUserPosts(@PathVariable String username , @PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
        return postService.getPostsOfUser(username, page).map(PostDTO::new);
    }
}
