package me.realpraveen.gateway.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.realpraveen.gateway.Applications.BookServiceClient;
import me.realpraveen.gateway.DTO.BookUserCombiner;
import me.realpraveen.gateway.DTO.Book.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

	private BookServiceClient bookClient;

	@Autowired
	public GatewayController(BookServiceClient bookClient) {
		this.bookClient = bookClient;
	}

	@GetMapping("/allbooks")
	public ResponseEntity<Flux<Book>> getAllBooks() {
		return ResponseEntity.ok(bookClient.getAllBooks());
	}

	@GetMapping
	public ResponseEntity<Mono<Long>> getAllBooksOfUser(@RequestParam(name = "userid") Long userId) {
		if (userId == null)
			return null;
		return ResponseEntity.ok(bookClient.getAllBooksOfUser(userId));
	}

	@GetMapping("/book/{bookId}")
	public ResponseEntity<Mono<BookUserCombiner>> getBookDetails(@PathVariable Long bookId) {
		return ResponseEntity.ok(bookClient.getBookDetails(bookId));
	}

}
