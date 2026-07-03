package com.schmgmt.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.schmgmt.models.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BookRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Book> findAll(){
		return entityManager.createNamedQuery("Book.findAllBooks", Book.class).getResultList();
	}
}
