package me.realpraveen.book_detail_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import me.realpraveen.book_detail_service.Model.Book;
import me.realpraveen.book_detail_service.Service.BookService;

@RequestMapping("/book")
public class BookContoller {

	private BookService bookService;

	@Autowired
	public BookContoller(BookService bookService) {
		this.bookService = bookService;
	}

	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

}
