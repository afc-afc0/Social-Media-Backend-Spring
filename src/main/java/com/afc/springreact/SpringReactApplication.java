package com.afc.springreact;

import com.afc.springreact.post.PostService;
import com.afc.springreact.post.dto.PostSubmitDTO;
import com.afc.springreact.user.User;
import com.afc.springreact.user.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringReactApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringReactApplication.class, args);
	}

	// @Bean
	// @Profile("dev")
	// CommandLineRunner createInitialUsers(UserService userService, PostService postService){
	// 	return  (args) -> {
	// 		for (int i = 1; i < 10; i++){
	// 			User user = new User();
	// 			user.setUsername("user" + i);
	// 			user.setDisplayName("display" + i);
	// 			user.setPassword("Pas4word!");
	// 			userService.save(user);

	// 			for (int j = 1; j <= 20; j++) {
	// 				PostSubmitDTO post = new PostSubmitDTO();
	// 				post.setContent("Post - " + j + " from user " + i);
	// 				postService.save(post, user);
	// 			} 
	// 		}
	// 	};
	// }
}



