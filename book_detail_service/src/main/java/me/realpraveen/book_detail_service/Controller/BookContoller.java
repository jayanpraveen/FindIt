package me.realpraveen.book_detail_service.Controller;

import java.util.List;
import java.util.Map;

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
import net.minidev.json.JSONObject;

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
		return ResponseEntity.ok(!books.isEmpty() ? books : Map.of("error", "not found"));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Long id) {
		var d = bookService.getBookById(id);
		return ResponseEntity.ok( d != null ?  d : new JSONObject(Map.of("error", "not found")));
	}

	@PostMapping
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.saveBook(book));
	}

}
