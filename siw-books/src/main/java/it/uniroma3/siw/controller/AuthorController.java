package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.sessionData.SessionData;

@Controller
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	@Autowired
	private SessionData sessionData;
	
	
	@GetMapping("/author/{id}")
	public String getAuthor(@PathVariable("id") Long id, Model model) {
		model.addAttribute("author", this.authorService.getAuthorById(id));
		return "author.html";
	}
	
	@GetMapping("/authors") 
	public String showAuthors(Model model) {
		User loggedUser = this.sessionData.getLoggedUser();
		model.addAttribute("user", loggedUser);
		model.addAttribute("authors", this.authorService.getAllAuthors());
		return "authors.html";
	}

}
