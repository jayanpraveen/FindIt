package me.realpraveen.book_detail_service.Model;

import javax.persistence.Entity;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

	// todo: move messages to properties file

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@NotBlank
	@Max(value = 225, message = "Book title must be within 225 characters")
	private String title;

	@NotNull
	@Max(value = 50, message = "Authour Name must be within 50 characters")
	private String authour;

	@Size(min = 10, max = 13, message = "ISBN should be 10 to 13 characters")
	private int ISBN;

	// @Enumerated(EnumType.STRING)
	// private Category category;

}
