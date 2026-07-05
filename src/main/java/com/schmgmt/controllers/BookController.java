package com.schmgmt.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schmgmt.models.Book;
import com.schmgmt.services.BookService;

@RestController
@RequestMapping("/schmgmt/api/books")
public class BookController {
	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book) {
		return this.bookService.addBook(null);
	}
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks(){
		return this.bookService.getAllBooks();
	}
	
	@GetMapping("/getBookById/{id}")
	public List<Book> getBookById(@PathVariable Long id){
		return this.bookService.getBookById(id);
	}
	
	@PutMapping("/updateBookById/{id}")
	public Book updateBook(@RequestBody Book updatedBook, @PathVariable Long id) {
		return this.bookService.updateBook(updatedBook, id);
	}
}
