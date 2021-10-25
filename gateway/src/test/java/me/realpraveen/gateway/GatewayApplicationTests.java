package me.realpraveen.gateway;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.realpraveen.gateway.Applications.BookServiceClient;
import me.realpraveen.gateway.Controller.GatewayController;
import me.realpraveen.gateway.DTO.Book.Book;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DirtiesContext
@EnableStubRunnerServer
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(ids = "me.realpraveen:book_detail_service:+:8081", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class GatewayApplicationTests {

    static {
        System.setProperty("eureka.client.enabled", "false");
    }

    @Autowired
    BookServiceClient bookClient;

    @Test
    public void contextLoads() {

        Flux<Book> book = this.bookClient.getAllBooks();

        StepVerifier.create(book).expectNextMatches(b -> {
            return b.getUserId() == 675L && b.getTitle().equalsIgnoreCase("awesome title");
        }).verifyComplete();

    }

}
