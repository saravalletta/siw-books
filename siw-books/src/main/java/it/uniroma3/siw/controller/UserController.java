package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.ReviewDto;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.sessionData.SessionData;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private CredentialsService credentialsService;
	@Autowired private ReviewService reviewService;
	@Autowired private BookService bookService;
	@Autowired private SessionData sessionData;
	
	// GESTIONE ACCOUNT
	@GetMapping("/account")
	public String showAccount(Model model) {
		Credentials credentials = this.sessionData.getLoggedCredentials();
	    User user = this.userService.getUserById(credentials.getUser().getId());

		model.addAttribute("credentials", credentials);
		model.addAttribute("reviews", user.getReviews());
		return "account.html";
	}
	
	// GESTIONE RECENSIONI
	@GetMapping("/addReview/{bookId}")
	public String addReview(@PathVariable("bookId") Long bookId, Model model) {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setBookId(bookId);
		model.addAttribute("reviewDto", reviewDto);
		return "addReview.html";
	}
	
	@PostMapping("/addReview/{bookId}")
	public String insertReview(@Valid @ModelAttribute("reviewDto") ReviewDto reviewDto, @RequestParam("rating") int rating, 
			BindingResult reviewBindingResult, Model model) {
		if(!reviewBindingResult.hasErrors()) {
			Book book = this.bookService.getBookById(reviewDto.getBookId());
			User user = this.sessionData.getLoggedUser();
			reviewDto.setScore(rating);
			Review review = this.reviewService.createReview(reviewDto.getTitle(), reviewDto.getScore(), reviewDto.getText(), user, book);
			model.addAttribute("review", review);
			return "redirect:/book/" + book.getId();
		}
		else {
			System.out.println("Errori di validazione:");
    	    reviewBindingResult.getAllErrors().forEach(System.out::println);
    	    return "addReview.html"; 
		}
	}
	
	@GetMapping("/updateReview/{id}")
	public String updateReview(@PathVariable("id") Long id, Model model) {
		Review review = this.reviewService.getReviewById(id);
		User loggedUser = this.sessionData.getLoggedUser();
		System.err.println(loggedUser.getId());
		System.err.println(review.getUser().getId());
		System.err.println(loggedUser.getId() == review.getUser().getId());
		// Controllo che l'utente stia cercando di modificare una delle sue recensioni
		if(loggedUser.getId().equals(review.getUser().getId())) {
			ReviewDto reviewDto = new ReviewDto();
			reviewDto.copyReview(review.getTitle(), review.getScore(), review.getText(), review.getUser().getId(), review.getBook().getId());
			model.addAttribute("id", id);
			model.addAttribute("reviewDto", reviewDto);
			return "updateReview.html";
		}
		else {
			System.out.println("Utente non autorizzato a modificare questa recensione");
			return "redirect:/book/" + review.getBook().getId();
		}
	}
	
	@PostMapping("/updateReview/{id}")
	public String saveUpdatedReview(@Valid @ModelAttribute("reviewDto") ReviewDto reviewDto, @PathVariable("id") Long id,
			BindingResult reviewBindingResult, Model model) {
		if(!reviewBindingResult.hasErrors()) {
			Review review = this.reviewService.getReviewById(id);
			Book book = this.bookService.getBookById(reviewDto.getBookId());
			User user = this.userService.getUserById(reviewDto.getUserId());
			review.copyReview(reviewDto.getTitle(), reviewDto.getScore(), reviewDto.getText(), user, book);
			Review updatedReview = this.reviewService.save(review);
			model.addAttribute("review", updatedReview);
			return "redirect:/book/" + book.getId();
		}
		else {
			System.out.println("Errori di validazione:");
    	    reviewBindingResult.getAllErrors().forEach(System.out::println);
    	    return "updateReview.html"; 
		}
	}
	
	@GetMapping("/deleteReview/{id}")
	public String deleteReview(@PathVariable("id") Long id) {
		Review review = this.reviewService.getReviewById(id);
		Book book = review.getBook();
		this.reviewService.delete(id);
		return "redirect:/book/" + book.getId();
	}

}
