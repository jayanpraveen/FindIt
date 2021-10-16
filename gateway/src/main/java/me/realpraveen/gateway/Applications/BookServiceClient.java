package me.realpraveen.gateway.Applications;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import me.realpraveen.gateway.DTO.Book.Book;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookServiceClient {

	private final WebClient.Builder webClient;

	private final String BOOK_SERVICE_URI = "lb://Book-Detail-Service/book-service/";

	public Mono<Book> getBookDetails(Long id) {
		return webClient.build().get().uri(BOOK_SERVICE_URI + id)
				.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN).retrieve()
				.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus), clientResponse -> Mono.empty())
				.bodyToMono(Book.class);
	}

}
