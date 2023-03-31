package com.example.lostandfound;

import com.example.lostandfound.entity.User;
import com.example.lostandfound.repository.MapUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class LostandFoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(LostandFoundApplication.class, args);
	}

	@Bean
	public MapUserRepository userRepository() {
//		String encodedPassword = pass().encode("password");

		// the raw password is "password"
        String encodedPassword = "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm";

		User user = new User();
		user.setNickname("ilpvc");
		user.setEmail("ilpvc@qq.com");
		user.setPassword(encodedPassword);
		Map<String, User> emailToCustomUser = new HashMap<>();
		emailToCustomUser.put(user.getEmail(), user);
		return new MapUserRepository(emailToCustomUser);
	}
}
