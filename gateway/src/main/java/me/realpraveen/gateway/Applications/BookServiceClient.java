package me.realpraveen.gateway.Applications;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import me.realpraveen.gateway.DTO.BookUserCombiner;
import me.realpraveen.gateway.DTO.Book.Book;
import me.realpraveen.gateway.DTO.User.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookServiceClient {

	private final WebClient.Builder webClient;

	private final String BOOK_SERVICE_URI = "lb://Book-Detail-Service/book-service";
	private final String USER_SERVICE_URI = "lb://User-Detail-Service/user-service";

	public Mono<BookUserCombiner> getBookDetails(Long id) {
		Mono<Book> bookDetail = webClient.build()
										 .get()
										 .uri(BOOK_SERVICE_URI + "/" + id)
										 .accept(MediaType.APPLICATION_JSON).retrieve()
										 .onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus), clientResponse -> Mono.empty())
										 .bodyToMono(Book.class);

		Mono<User> userDetail = bookDetail.flatMap(b -> getUserDetails(b.getUserId()));
			
		return Mono.zip(bookDetail, userDetail, (b,u) -> new BookUserCombiner(b,u));
	}

	private Mono<User> getUserDetails(Long userId) {

		return webClient.build()
						.get()
						.uri(USER_SERVICE_URI + "/" +userId)
						.accept(MediaType.APPLICATION_JSON).retrieve()
						.bodyToMono(User.class);
							
	}

	public Flux<Book> getAllBooks() {

		return webClient.build()
					    .get()
						.uri(BOOK_SERVICE_URI)
						.accept(MediaType.APPLICATION_JSON).retrieve()
						.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus), clientResponse -> Mono.empty())
						.bodyToFlux(Book.class);		

	}

	public Flux<Book> getAllBooksOfUser(Long userId) {

		return webClient.build()
		                 .get()
		                 .uri(BOOK_SERVICE_URI + "/userbooks/" + userId)
		                 .accept(MediaType.APPLICATION_JSON).retrieve()
		                 .onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus), clientResponse -> Mono.empty())
		                 .bodyToFlux(Book.class);	

	}

	public Mono<Book> postNewBook(Book book){
		return webClient.build()
					.post()
					.uri(BOOK_SERVICE_URI)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(Mono.just(book), Book.class)
					.retrieve()
					.bodyToMono(Book.class);
	}

}
