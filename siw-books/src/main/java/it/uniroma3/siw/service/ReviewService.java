package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ReviewRepository;
import jakarta.transaction.Transactional;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional
	public Review createReview(String title, Integer score, String text, User user, Book book) {
		Review review = new Review(title, score, text, user, book);
		user.addReview(review);
		book.addReview(review);
		review = this.reviewRepository.save(review);
		return review;
	}
	
	public Review save(Review review) {
		return this.reviewRepository.save(review);
	}
	
	public Review getReviewById(Long id) {
		return reviewRepository.findById(id).orElse(null);
	}
	
	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

}
