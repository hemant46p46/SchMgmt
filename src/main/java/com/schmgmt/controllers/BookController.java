package com.schmgmt.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks(){
		return this.bookService.getAllBooks();
	}
}
