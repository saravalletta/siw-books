package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.AutoreRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AutoreRepository authorRepository;
	
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id).orElse(null);
	}
	
	public Iterable<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

}
