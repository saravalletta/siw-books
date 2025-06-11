package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public Review getReviewById(Long id) {
		return reviewRepository.findById(id).orElse(null);
	}
	
	public Iterable<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

}
