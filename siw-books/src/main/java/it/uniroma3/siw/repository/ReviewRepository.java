package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.model.Review;


public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	public List<Review> findAll();
	
	// Per ordinare le recensioni in ordine di creazione (dall'ultima alla prima)
	public List<Review> findTop10ByOrderByCreatedAtDesc();

}
