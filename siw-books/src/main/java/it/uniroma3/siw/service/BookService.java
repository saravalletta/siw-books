package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.LibroRepository;

@Service
public class BookService {
	
	@Autowired
	private LibroRepository bookRepository;
	
	public Book getBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	public Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}

}
