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
	
	public Book addBook(Book book) {
		return this.bookRepository.addBook(book);
	}
	
	public List<Book> getAllBooks(){
		return this.bookRepository.findAll();
	}
	
	public List<Book> getBookById(Long id){
		return this.bookRepository.findById(id);
	}
	
	public Book updateBook(Book updatedBook, Long id) {
		return this.bookRepository.updateBook(updatedBook, id);
	}
}
