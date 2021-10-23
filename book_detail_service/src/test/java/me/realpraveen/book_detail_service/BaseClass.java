package me.realpraveen.book_detail_service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.RestAssured;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import me.realpraveen.book_detail_service.Controller.BookContoller;
import me.realpraveen.book_detail_service.Model.Book;
import me.realpraveen.book_detail_service.Model.Category;
import me.realpraveen.book_detail_service.Repository.BookRepository;
import me.realpraveen.book_detail_service.Service.BookService;

// @Import(BookService.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class BaseClass {

	@MockBean
	BookRepository bookRepo;

	@LocalServerPort
	int port;

	@Autowired
	private WebTestClient testClient;

	// @Autowired
	// WebApplicationContext ctx;

	// @Autowired
	// BookContoller contoller;

	@BeforeEach
	public void before() {

		RestAssured.baseURI = "http://localhost:" + port;

		// testClient = WebTestClient.bindToController(contoller).build();
		RestAssuredWebTestClient.webTestClient(this.testClient);

		Mockito.when(bookRepo.findAll())
				.thenReturn(List.of(new Book(10L, 675L, "awesome title", "cow", 2345, Category.UNLISTED)));

	}

}
