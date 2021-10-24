package me.realpraveen.book_detail_service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import io.restassured.RestAssured;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import me.realpraveen.book_detail_service.Model.Book;
import me.realpraveen.book_detail_service.Model.Category;
import me.realpraveen.book_detail_service.Repository.BookRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class BaseClass {

	@MockBean
	BookRepository bookRepo;

	@LocalServerPort
	int port;

	@Autowired
	private WebTestClient testClient;

	@BeforeEach
	public void before() {

		RestAssured.baseURI = "http://localhost:" + port;

		RestAssuredWebTestClient.webTestClient(this.testClient);

		Mockito.when(bookRepo.findAll())
				.thenReturn(List.of(new Book(10L, 675L, "awesome title", "cow", 2345, Category.UNLISTED)));

	}

}
