package me.realpraveen.gateway;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import me.realpraveen.gateway.Applications.BookServiceClient;
import me.realpraveen.gateway.Controller.GatewayController;
import me.realpraveen.gateway.DTO.BookUserCombiner;
import me.realpraveen.gateway.DTO.Book.Book;
import me.realpraveen.gateway.DTO.Book.Category;
import me.realpraveen.gateway.DTO.User.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DirtiesContext
@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(
        ids = {"me.realpraveen:book_detail_service:+:8081", "me.realpraveen:user_service:+:8082"}, 
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class GatewayApplicationTests {

    /**
     * work around for
     * https://github.com/spring-cloud/spring-cloud-contract/issues/141
     */

    static {
        System.setProperty("eureka.client.enabled", "false");
        System.setProperty("eureka.client.register-with-eureka", "false");
        System.setProperty("spring.cloud.gateway.discovery.locator.enabled", "false");
    }

    @Autowired
    private BookServiceClient bookClient;

    @Autowired 
    private WebTestClient webTestClient;

    @Autowired
    private GatewayController controller;

    @Test
    public void shouldReturnAllBooks() {

        Flux<Book> book = this.bookClient.getAllBooks();

        StepVerifier.create(book)
                    .expectNextMatches(b1 -> b1.getAuthour().equals("Jimmy"))
                    .expectNextMatches(b2 -> b2.getAuthour().equals("cow"))
                    .verifyComplete();

        StepVerifier.create(book)
                    .expectNext(new Book(12L, 45L, "Random fandom", "Jimmy", 8422, Category.SCIENCE))
                    .expectNext(new Book(10L, 675L, "awesome title", "cow", 2345, Category.UNLISTED))
                    .verifyComplete();
    }

    @Test
    void postBookTest(){

       webTestClient = WebTestClient.bindToController(controller).build();

       Book bookBody = new Book(849L, 4523L, "Random fandom", "Jimmy", 8422, Category.SCIENCE);

       webTestClient.post()
		            .uri("/api/gateway/book")
				    .header(MediaType.APPLICATION_JSON_VALUE)
				    .bodyValue(bookBody)
				    .exchange()
				    .expectStatus().isCreated();
        
    }

    @Test
    void shouldReturnBookDetails() {

        Mono<BookUserCombiner> details = this.bookClient.getBookDetails(12L);

        Book b = new Book(12L, 45L, "Random fandom", "Jimmy", 8422, Category.SCIENCE);
        User u = new User("david", "dave@dom.com", "somescl");
        
        var expectDetail = new BookUserCombiner(b, u);

        StepVerifier.create(details)
                    .expectNext(expectDetail)
                    .verifyComplete();

    }

    @Test
    void shouldReturnBooksForUserId() {

         Flux<Book> books = this.bookClient.getAllBooksOfUser(675L);
         
         StepVerifier.create(books)
                     .expectNext(new Book(10L, 675L, "awesome title", "cow", 2345, Category.UNLISTED))
                     .expectNext(new Book(11L, 675L, "some book", "alex", 9876, Category.UNLISTED))
                     .verifyComplete();

        StepVerifier.create(books)
                     .expectNextMatches(b -> b.getISBN() == 2345)
                     .expectNextMatches(b -> b.getISBN() == 9876)
                     .verifyComplete();

    }

}