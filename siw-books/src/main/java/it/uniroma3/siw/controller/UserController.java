package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
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
	@Autowired private SessionData sessionData;
	
	// GESTIONE ACCOUNT
	@GetMapping("/account")
	public String showAccount(Model model) {
		Credentials credentials = this.sessionData.getLoggedCredentials();
		// Carico l'utente con le recensioni già inizializzate per evitare problemi di lazying
	    User userWithReviews = this.userService.getUserWithReviews(credentials.getUser().getId());

		model.addAttribute("credentials", credentials);
		model.addAttribute("reviews", userWithReviews.getReviews());
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
	/*@GetMapping("/addReview")
	public String addReview(Model model) {
		
	}*/

}
