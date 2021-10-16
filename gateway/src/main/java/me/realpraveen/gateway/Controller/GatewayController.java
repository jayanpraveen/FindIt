package me.realpraveen.gateway.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.realpraveen.gateway.Applications.BookServiceClient;
import me.realpraveen.gateway.DTO.Book.Book;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

	private BookServiceClient bookClient;

	@Autowired
	public GatewayController(BookServiceClient bookClient) {
		this.bookClient = bookClient;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Mono<Book>> getBookDetails(@PathVariable Long id) {
		return ResponseEntity.ok(bookClient.getBookDetails(id));
	}

}
