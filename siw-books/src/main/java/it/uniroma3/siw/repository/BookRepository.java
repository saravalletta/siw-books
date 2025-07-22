package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findAll();
	
	// Per la ricerca
	@Query("select b from Book b where CONCAT(b.id,'',b.title) LIKE %?1%")
    public List<Book> findAllWithThatKeyword(String keyword);

}
