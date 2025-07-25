package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.uniroma3.siw.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	public List<Book> findAll();
	
	// Per ordinare i libri in ordine di creazione (dall'ultimo al primo)
	public List<Book> findTop10ByOrderByCreatedAtDesc();
	
	// Per la ricerca
	@Query("select b from Book b where CONCAT(b.id,'',b.title) LIKE %?1%")
    public Set<Book> findAllWithThatKeyword(String keyword);

}
