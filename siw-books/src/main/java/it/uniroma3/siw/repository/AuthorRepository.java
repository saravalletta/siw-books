package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	
	public List<Author> findAll();

}
