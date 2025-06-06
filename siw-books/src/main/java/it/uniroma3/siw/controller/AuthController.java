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
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired private CredentialsService credenzialiService;
	@Autowired private UserService utenteService;
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/success")
	public String defaultAfterLogin() {
		
	}
	
	@GetMapping("/register")
	public String showRegisterUser(Model model) {
		
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult userBindingResult, 
			@Valid @ModelAttribute("credentials") Credentials credentilals, BindingResult credentialsBindingResult,
			Model model) {
		
	}

}
