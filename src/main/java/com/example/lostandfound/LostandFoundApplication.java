package com.example.lostandfound;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LostandFoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(LostandFoundApplication.class, args);
	}

}
