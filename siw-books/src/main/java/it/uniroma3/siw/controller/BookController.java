package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.sessionData.SessionData;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private SessionData sessionData;
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/book/{id}")
	public String getBook(@PathVariable("id") Long id, Model model) {
		Book book = this.bookService.getBookById(id);
		List<Review> reviews = book.getReviews();
		User user = this.sessionData.getLoggedUser();

		if(user != null) {
			model.addAttribute("userId", user.getId());
			boolean hasReviewed = this.reviewService.hasReview(book.getId(), user.getId());
			
			if(hasReviewed) {
				Review userReview = this.reviewService.getReviewByUserAndBook(user.getId(), book.getId());
				// rimuovo la recensione dell'utente dalla lista per evitare duplicati
				reviews.remove(userReview);
				// rimetto la recensione dell'utente in cima alla lista
				reviews.add(0, userReview);
			}
			model.addAttribute("hasReviewed", hasReviewed);
		}
		
		model.addAttribute("book", book);
		model.addAttribute("reviews", reviews);
		return "book.html";
	}
	
	@GetMapping("/books")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.getAllBooks());
		model.addAttribute("bestsellers", this.bookService.getBooksWithBestScore());
		return "books.html";
	}

}
