package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Review;


public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	public List<Review> findAll();
	
	// Per ordinare le recensioni in ordine di creazione (dall'ultima alla prima)
	public List<Review> findTop10ByOrderByCreatedAtDesc();

	// Per verificare se un utente ha già scritto una recensione per un determinato libro
	@Query("SELECT COUNT(r) > 0 FROM Review r WHERE r.book.id = :bookId AND r.user.id = :userId")
	boolean existsByBookIdAndUserId(@Param("bookId") Long bookId, @Param("userId") Long userId);

	// Per prendere la recensione scritta dall'utente corrente (se esiste) per un determinato libro
	public Review findByUserIdAndBookId(Long userId, Long bookId);
}
