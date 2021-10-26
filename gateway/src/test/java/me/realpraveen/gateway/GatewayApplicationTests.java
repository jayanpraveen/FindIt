package me.realpraveen.gateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.realpraveen.gateway.Applications.BookServiceClient;
import me.realpraveen.gateway.DTO.Book.Book;
import me.realpraveen.gateway.DTO.Book.Category;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DirtiesContext
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(ids = {
        "me.realpraveen:book_detail_service:+:8081" }, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class GatewayApplicationTests {

    /**
     * work around for
     * https://github.com/spring-cloud/spring-cloud-contract/issues/141
     */

    static {
        System.setProperty("eureka.client.enabled", "false");
        System.setProperty("spring.cloud.gateway.discovery.locator.enabled", "false");
    }

    @Autowired
    BookServiceClient bookClient;

    @BeforeEach
    public void before() {
    }

    @Test
    public void shouldReturnAllBooks() {

        Flux<Book> book = this.bookClient.getAllBooks();

        StepVerifier.create(book).expectNextMatches(b1 -> b1.getAuthour().equals("Jimmy"))
                .expectNextMatches(b2 -> b2.getAuthour().equals("cow")).verifyComplete();

        StepVerifier.create(book).expectNext(new Book(12L, 45L, "Random fandom", "Jimmy", 8422, Category.SCIENCE))
                .expectNext(new Book(10L, 675L, "awesome title", "cow", 2345, Category.UNLISTED)).verifyComplete();
    }

    @Test
    void shouldReturnBookDetails() {

    }

    @Test
    void shouldReturnBooksForUserId() {

        // Flux<Book> books = this.bookClient.getAllBooksOfUser(12L);

    }

}