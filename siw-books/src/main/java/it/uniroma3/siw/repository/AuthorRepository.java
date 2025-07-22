package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	
	public List<Author> findAll();
	
	public List<Author> findAllById(List<Long> authorsIds);
	
	// Per la ricerca 
	@Query("select a from Author a where concat(a.id,'',a.name,'',a.surname,'',a.nationality) LIKE %?1%")
    public List<Author> findAllWithThatKeyWord(String keyWord);

}
