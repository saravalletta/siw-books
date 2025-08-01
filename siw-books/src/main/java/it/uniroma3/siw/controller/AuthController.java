package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.UserDto;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.sessionData.SessionData;
import jakarta.validation.Valid;

import static it.uniroma3.siw.model.Credentials.DEFAULT;

import java.util.List;
import java.util.Set;

import static it.uniroma3.siw.model.Credentials.ADMIN;

@Controller
public class AuthController {
	
	@Autowired private CredentialsService credentialsService;
	@Autowired private UserService userService;
	@Autowired private ReviewService reviewService;
	@Autowired private BookService bookService;
	@Autowired private SessionData sessionData;
	
	@GetMapping("/") 
	public String getHomePage(Model model) {
		List<Book> lastBooks  = this.bookService.getLast10Books(); // Per prendere gli ultimi 10 libri inseriti
		List<Review> lastReviews = this.reviewService.getLast10Reviews(); // Per prendere le ultime 10 recensioni inserite
		model.addAttribute("books", lastBooks);
		model.addAttribute("reviews", lastReviews);
		return "homepage.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/success")
	public String defaultAfterLogin(Model model) {
		UserDetails userDetails = null;
		Credentials credentials = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth instanceof AnonymousAuthenticationToken) {
			List<Book> lastBooks  = this.bookService.getLast10Books(); // Per prendere gli ultimi 10 libri inseriti
			List<Review> lastReviews = this.reviewService.getLast10Reviews(); // Per prendere le ultime 10 recensioni inserite
			model.addAttribute("books", lastBooks);
			model.addAttribute("reviews", lastReviews);
			return "homepage.html";
		}
		else {
			userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			credentials = this.credentialsService.getCredentialsByUsername(userDetails.getUsername());
			User loggedUser = this.sessionData.getLoggedUser();
			model.addAttribute("user", loggedUser);
			List<Book> lastBooks  = this.bookService.getLast10Books(); // Per prendere gli ultimi 10 libri inseriti
			List<Review> lastReviews = this.reviewService.getLast10Reviews(); // Per prendere le ultime 10 recensioni inserite
			model.addAttribute("books", lastBooks);
			model.addAttribute("reviews", lastReviews);
			
			if(credentials.getRole().trim().equals(ADMIN)) {
				return "homepage.html";
			}
		}
		List<Book> lastBooks  = this.bookService.getLast10Books(); // Per prendere gli ultimi 10 libri inseriti
		List<Review> lastReviews = this.reviewService.getLast10Reviews(); // Per prendere le ultime 10 recensioni inserite
		model.addAttribute("books", lastBooks);
		model.addAttribute("reviews", lastReviews);
		return "homepage.html";
	}
	
	@GetMapping("/register")
	public String showRegisterUser(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "register.html";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult userBindingResult,
			Model model) {
		if(!userBindingResult.hasErrors()) {
			User user = this.userService.createUser(userDto.getName(), userDto.getSurname(), userDto.getEmail());
			Credentials credentials = this.credentialsService.createCredentials(userDto.getUsername(), userDto.getPassword(), DEFAULT, user);
			model.addAttribute("user", user);
			return "login.html";
		}
		return "register.html";
	}

}
