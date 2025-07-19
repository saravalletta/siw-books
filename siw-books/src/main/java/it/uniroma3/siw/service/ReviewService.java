package it.uniroma3.siw.service;

import java.util.List;

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
	
	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

}
