package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.sessionData.SessionData;

@Controller
public class HomePageController {
	
	@Autowired SessionData sessionData;
	
	@GetMapping("/") 
	public String getHomePage(Model model) {
		return "homepage.html";
	}

}
