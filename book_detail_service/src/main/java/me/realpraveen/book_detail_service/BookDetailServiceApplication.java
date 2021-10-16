package me.realpraveen.book_detail_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BookDetailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookDetailServiceApplication.class, args);
	}

}
