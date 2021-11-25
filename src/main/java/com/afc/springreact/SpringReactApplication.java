package com.afc.springreact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class SpringReactApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringReactApplication.class, args);
	}

	@Bean
	CommandLineRunner createInitialUsers(){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception{
				
			}
		};
	}
}



