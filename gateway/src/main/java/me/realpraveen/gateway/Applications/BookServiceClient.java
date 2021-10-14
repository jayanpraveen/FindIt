package me.realpraveen.gateway.Applications;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import me.realpraveen.gateway.DTO.Book;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookServiceClient {

	private final WebClient.Builder webClient;

	private final String BOOK_SERVICE_URI = "http://Book-Detail-Service/book";

	public Mono<String> getBookDetails() {
		return webClient.build().get().uri(BOOK_SERVICE_URI).accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
				.retrieve().bodyToMono(String.class);
	}

}
