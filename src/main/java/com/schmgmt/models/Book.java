package com.schmgmt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@NamedQueries({
	@NamedQuery(name = "Book.findAllBooks", query = "SELECT b from Book b "),
	@NamedQuery(name = "Book.findBookById", query = "SELECT b from Book b WHERE b.id = :id")
})
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long Id;
	
	@NotBlank(message = "Title is required")
	@Size(max = 250)
	private String title;
	
	@NotBlank(message = "Author is required")
	@Size(max = 150)
	private String author;
	
	@Size(max = 20)
	@Column(unique = true)
	private String isbn;
	
	@Column(name = "publish_year")
	private Integer publishYear;
	
	@Column(name = "total_copies")
	private Integer totalCopies;
	
	@Column(name = "available_copies")
	private Integer availableCopies;
	
	@Column(name = "course_id")
	private Integer courseId;
}
