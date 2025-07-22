package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.uniroma3.siw.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	public List<Author> findAll();
	
	// Per la ricerca 
	@Query("select a from Author a where concat(a.id,'',a.name,'',a.surname,'',a.nationality) LIKE %?1%")
    public List<Author> findAllWithThatKeyWord(String keyWord);

}
