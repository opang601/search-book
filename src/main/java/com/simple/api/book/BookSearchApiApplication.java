package com.simple.api.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class BookSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSearchApiApplication.class, args);
	}

}
