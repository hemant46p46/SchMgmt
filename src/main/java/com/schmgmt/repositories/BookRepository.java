package com.schmgmt.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.schmgmt.models.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class BookRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Book addBook(Book book) {
		this.entityManager.persist(book);
		return book;
	}
	
	public List<Book> findAll(){
		return this.entityManager.createNamedQuery("Book.findAllBooks", Book.class).getResultList();
	}
	
	public List<Book> findById(Long id){
		return this.entityManager.createNamedQuery("Book.findBookById", Book.class).setParameter("id", id).getResultList();
	}
	
	@Transactional
	public Book updateBook(Book updatedBook, Long id) {
		int updatedRows = this.entityManager.createNamedQuery("Book.updateBookById")
		        .setParameter("title", updatedBook.getTitle())
		        .setParameter("author", updatedBook.getAuthor())
		        .setParameter("isbn", updatedBook.getIsbn())
		        .setParameter("publishYear", updatedBook.getPublishYear())
		        .setParameter("totalCopies", updatedBook.getTotalCopies())
		        .setParameter("availableCopies", updatedBook.getAvailableCopies())
		        .setParameter("courseId", updatedBook.getCourseId())
		        .setParameter("id", id)
		        .executeUpdate();

		
	    if (updatedRows == 0) {
	        throw new RuntimeException("Book not found with id: " + id);
	    }
	    
	    updatedBook.setId(id);
	    return updatedBook;
	}
	
	public void deleteBook(Long id) {
		int deletedRows = this.entityManager.createNamedQuery("Book.deleteBook", Book.class)
				.setParameter("id", id)
				.executeUpdate();
		
	    if (deletedRows == 0) {
	        throw new RuntimeException("Book not found with id: " + id);
	    }
	}
}
