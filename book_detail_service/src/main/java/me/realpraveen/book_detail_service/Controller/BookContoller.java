package me.realpraveen.book_detail_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.realpraveen.book_detail_service.Model.Book;
import me.realpraveen.book_detail_service.Service.BookService;

@RequestMapping("/book")
@RestController
public class BookContoller {

	private BookService bookService;

	@Autowired
	public BookContoller(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public ResponseEntity<String> hello() {
		List<Book> books = bookService.getAllBooks();
		return ResponseEntity.ok("the book list goes here");
	}

	@PostMapping
	public Book saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

}
