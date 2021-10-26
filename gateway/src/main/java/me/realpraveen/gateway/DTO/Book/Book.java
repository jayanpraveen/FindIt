package me.realpraveen.gateway.DTO.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private Long bookId;
	private Long userId;
	private String title;
	private String authour;
	private int ISBN;
	private Category category;

}
