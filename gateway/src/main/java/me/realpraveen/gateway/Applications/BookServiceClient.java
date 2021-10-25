package me.realpraveen.gateway.Applications;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import me.realpraveen.gateway.DTO.Book.Book;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class BookServiceClient {

	// private final WebClient webClient;

	private final WebClient.Builder webClient;

	private String BOOK_SERVICE_URI = "http://Book-Detail-Service/book-service";

	public Flux<Book> getAllBooks(){
		Flux<Book> bookDetail = webClient.build()
								.get()
								// .uri("http://localhost:8081/book-service")
								.uri(BOOK_SERVICE_URI)
								.accept(MediaType.APPLICATION_JSON).retrieve()
								.bodyToFlux(Book.class);
		return	bookDetail;	
	}

}
