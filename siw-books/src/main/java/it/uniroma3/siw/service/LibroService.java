package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	public Book getLibroById(Long id) {
		return libroRepository.findById(id).orElse(null);
	}
	
	public Iterable<Book> getAllLibri() {
		return libroRepository.findAll();
	}

}
