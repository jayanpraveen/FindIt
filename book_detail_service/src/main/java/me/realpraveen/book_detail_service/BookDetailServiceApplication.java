package me.realpraveen.book_detail_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class BookDetailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookDetailServiceApplication.class, args);
	}

	@RequestMapping(value = "/available")
	public String available() {
		return "Spring in Action";
	}

	@RequestMapping(value = "/checked-out")
	public String checkedOut() {
		return "Spring Boot in Action";
	}

}
