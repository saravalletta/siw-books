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
	
	@GetMapping("/updateAccount")
	public String updateAccount( Model model) {
		Credentials credentials = this.sessionData.getLoggedCredentials();
		model.addAttribute("credentials", credentials);
		return "updateAccount.html";
	}
	
	@PostMapping("/updateAccount")
	public String saveUpdatedAccount(@Valid @ModelAttribute("credentials") Credentials credentials, 
			BindingResult accountBindingResult, Model model) {
		if(!accountBindingResult.hasErrors()) {
			// Verifica se esiste già un altro utente con lo stesso username
	        if (credentialsService.existsByUsernameAndNotId(credentials.getUsername(), credentials.getId())) {
	            model.addAttribute("message", "Il nome utente è già in uso.");
	            model.addAttribute("credentials", credentials);
	            return "updateAccount.html";
	        }

	        // Verifica se esiste già un altro utente con la stessa email
	        if (userService.existsByEmailAndNotId(credentials.getUser().getEmail(), credentials.getUser().getId())) {
	            model.addAttribute("message", "L'email è già in uso.");
	            model.addAttribute("credentials", credentials);
	            return "updateAccount.html";
	        }
	        
	        this.userService.save(credentials.getUser());
	        this.credentialsService.save(credentials);
	        return "redirect:/account.html";

		}
		else {
			System.out.println("Errori di validazione:");
    	    accountBindingResult.getAllErrors().forEach(System.out::println);
    	    return "updateAccount.html";
		}
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

}
