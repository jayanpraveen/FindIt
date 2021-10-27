package me.realpraveen.user_service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import me.realpraveen.user_service.Controller.UserController;
import me.realpraveen.user_service.DTO.UserInfo;
import me.realpraveen.user_service.Model.Institution;
import me.realpraveen.user_service.Model.User;
import me.realpraveen.user_service.Repository.UserRepository;

@DirtiesContext
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class UserServiceApplicationTests {

	@LocalServerPort
	private int port;

	@MockBean
	private UserRepository userRepo;

	@Autowired
	private WebTestClient webtestClient;

	@Autowired
	private UserController controller;

	@Autowired
	private WebApplicationContext context;

	static {
		System.setProperty("eureka.client.enabled", "false");
		System.setProperty("eureka.client.register-with-eureka", "false");
		System.setProperty("spring.cloud.gateway.discovery.locator.enabled", "false");
	}

	@BeforeEach
	public void before() throws Exception {
		RestAssured.baseURI = "http://localhost:" + port;
		RestAssuredWebTestClient.webTestClient(this.webtestClient);
		RestAssuredMockMvc.webAppContextSetup(context);

		User user = new User(45L, "david", "dave@dom.com", "$2bd%osj", Institution.SCHOOL, "somescl");
		Mockito.when(userRepo.findById(45L)).thenReturn(Optional.of(user));
	}

	@Test
	void getUserTest() {

		webtestClient = WebTestClient.bindToController(controller).build();

		UserInfo userInfo = new UserInfo(45L, "david", "dave@dom.com", Institution.SCHOOL, "somescl");
		
		webtestClient.get()
					 .uri("/user-service/45")
					 .header(MediaType.APPLICATION_JSON_VALUE)
					 .exchange()
					 .expectStatus().isOk()
					 .expectBody(UserInfo.class)
					 .isEqualTo(userInfo);

	}

	@Test
	void postNewUser() {
	
		webtestClient = WebTestClient.bindToController(controller).build();

		User userBody = new User(45L, "david", "dave@dom.com", "2bd%osj", Institution.SCHOOL, "somescl");
		
		webtestClient.post()
					 .uri("/user-service")
					 .header(MediaType.APPLICATION_JSON_VALUE)
					 .bodyValue(userBody)
					 .exchange()
					 .expectStatus().isCreated();		
		
	}

}
