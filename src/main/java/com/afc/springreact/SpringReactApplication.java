package com.afc.springreact;

import com.afc.springreact.user.User;
import com.afc.springreact.user.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringReactApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringReactApplication.class, args);
	}

	// @Bean
	// CommandLineRunner createInitialUsers(UserService userService){
	// 	return  (args) -> {
	// 		for (int i = 1; i < 10; i++){
	// 			User user = new User();
	// 			user.setUsername("user" + i);
	// 			user.setDisplayName("display" + i);
	// 			user.setPassword("Pas4word!");
	// 			userService.save(user);
	// 		}
	// 	};
	// }
}



