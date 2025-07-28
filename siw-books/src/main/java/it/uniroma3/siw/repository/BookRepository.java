package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	public List<Book> findAll();
	
	// Per ordinare i libri in ordine di creazione (dall'ultimo al primo)
	public List<Book> findTop10ByOrderByCreatedAtDesc();
	
	// Per la ricerca
	@Query("SELECT b FROM Book b WHERE " + "LOWER(CONCAT(b.title, '', b.year, '', b.id)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchByKeyword(@Param("keyword") String keyword);
	
	// Per selezionare i libri scritti da un determinato autore
    @Query(value = "SELECT b.* FROM Book b JOIN book_author ba ON ba.book_id = b.id WHERE ba_author.id =?1",nativeQuery = true)
    public List<Book> findBooksWrittenBy(Long authorId);
    
    @Query(value = "SELECT b.*, AVG(r.score) as avg " +
            "FROM Book b " +
            "JOIN Review r ON b.id = r.book_id " +
            "GROUP BY b.id " +
            "ORDER BY avg DESC", nativeQuery = true)
    public List<Book> findBooksWithBestScore();

}
