package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id).orElse(null);
	}
	
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

}
