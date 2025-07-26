package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	public List<Author> findAll();
	
	// Per la ricerca 
	@Query("select a from Author a where CONCAT(a.id,'',a.name,'',a.surname,'',a.nationality) LIKE %?1%")
    public List<Author> findAllWithThatKeyWord(String keyWord);
	
	@Query(value = "SELECT b.* FROM Book b JOIN book_author ba ON b.id = ba.book_id WHERE ba.author_id = ?1", nativeQuery = true)
    public List<Book> findBooksByAuthorId(Long authorId);
	
	// Per trovare gli autori che hanno scritto un libro
    @Query(value = "SELECT a.* FROM Author a JOIN book_author ba ON a.id = ba.author_id WHERE ba.book_id =?1", nativeQuery = true)
    public List<Author> authorsOfBook(Long bookId);

}
