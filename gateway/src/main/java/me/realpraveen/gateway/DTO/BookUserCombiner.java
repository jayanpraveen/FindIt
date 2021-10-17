package me.realpraveen.gateway.DTO;

import lombok.Data;
import me.realpraveen.gateway.DTO.Book.Book;
import me.realpraveen.gateway.DTO.Book.Category;
import me.realpraveen.gateway.DTO.User.User;

@Data
public class BookUserCombiner {

	private String name;
	private String email;
	private String instituteName;

	private Long bookId;
	private String title;
	private String authour;
	private int ISBN;
	private Category category;

	public BookUserCombiner(Book b, User u) {
		this.name = u.getName();
		this.email = u.getEmail();
		this.instituteName = u.getInstituteName();

		this.bookId = b.getBookId();
		this.title = b.getTitle();
		this.authour = b.getAuthour();
		this.ISBN = b.getISBN();
		this.category = b.getCategory();

	}

}
