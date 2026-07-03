package com.schmgmt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schmgmt.models.Book;
import com.schmgmt.repositories.BookRepository;

@Service
public class BookService {
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> getAllBooks(){
		return this.bookRepository.findAll();
	}
}
