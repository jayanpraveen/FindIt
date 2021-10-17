package me.realpraveen.gateway.DTO.Book;

import lombok.Data;

@Data
public class Book {

	private Long bookId;
	private Long userId;
	private String title;
	private String authour;
	private int ISBN;
	private Category category;
}
