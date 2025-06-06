package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class AuthController {
	
	@Autowired private CredenzialiService credenzialiService;
	@Autowired private UtenteService utenteService;
	
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
	public String registerUser() {
		
	}

}
