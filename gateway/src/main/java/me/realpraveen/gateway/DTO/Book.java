package me.realpraveen.gateway.DTO;

import lombok.Data;

@Data
public class Book {

	private Long bookId;
	private String title;
	private String authour;
	private int ISBN;

}
