package me.realpraveen.gateway.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.realpraveen.gateway.Applications.BookServiceClient;
import me.realpraveen.gateway.DTO.Book.Book;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class GatewayController {

	private BookServiceClient bookClient;

	@Autowired
	public GatewayController(BookServiceClient bookClient) {
		this.bookClient = bookClient;
	}

	@GetMapping()
	public Flux<Book> getAllBooks() {
		return bookClient.getAllBooks();
	}

}
