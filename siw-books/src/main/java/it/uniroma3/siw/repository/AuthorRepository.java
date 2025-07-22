package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	
	public List<Author> findAll();
	
	@Query("select a from Autore a where concat(a.id,'',a.nome,'',a.cognome,'',a.nazionalita) LIKE %?1%")
    public List<Author> findAllWithThatKeyWord(String keyWord);

}
