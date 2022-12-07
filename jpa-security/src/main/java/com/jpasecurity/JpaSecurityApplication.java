package com.jpasecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpasecurity.model.Post;
import com.jpasecurity.model.User;
import com.jpasecurity.repository.PostRepository;
import com.jpasecurity.repository.UserRepository;

@SpringBootApplication
public class JpaSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaSecurityApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner (PostRepository posts, UserRepository users, PasswordEncoder encoder) {
		return args -> {
		users.save(new User("user", encoder.encode("password"), "ROLE_USER"));
		users.save(new User("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"));
		posts.save(new Post("Hello, World!", "hello-world", "Welcome to my blog!","Hasmat Noorani"));
	};
}

}
