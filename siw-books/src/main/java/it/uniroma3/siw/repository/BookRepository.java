package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findAll();
	
	@Query("select l from Libro l where CONCAT(l.id,'',l.titolo) LIKE %?1%")
    public Set<Book> findAllWithThatKeyword(String keyword);

}
