package me.realpraveen.book_detail_service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.realpraveen.book_detail_service.Model.Book;
import me.realpraveen.book_detail_service.Repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepo;

	@Autowired
	public BookService(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}

	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

}
