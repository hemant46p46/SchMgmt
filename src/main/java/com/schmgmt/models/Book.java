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
    @NamedQuery(name = "Book.findAllBooks", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findBookById", query = "SELECT b FROM Book b WHERE b.id = :id"),
    @NamedQuery(name = "Book.updateBookById", query = "UPDATE Book b SET b.title = :title, b.author = :author, b.isbn = :isbn, b.publishYear = :publishYear, b.totalCopies = :totalCopies, b.availableCopies = :availableCopies, b.courseId = :courseId WHERE b.id = :id"),
    @NamedQuery(name = "Book.deleteBook", query = "DELETE FROM Book b WHERE b.id = :id")
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
