package me.realpraveen.book_detail_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.realpraveen.book_detail_service.Model.Book;
import me.realpraveen.book_detail_service.Service.BookService;

@RestController
@RequestMapping("/book-service")
public class BookContoller {

	private BookService bookService;

	@Autowired
	public BookContoller(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public ResponseEntity<?> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return ResponseEntity.ok(!books.isEmpty() ? books : "Empty");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.getBookById(id));
	}

	@PostMapping
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.saveBook(book));
	}

}
