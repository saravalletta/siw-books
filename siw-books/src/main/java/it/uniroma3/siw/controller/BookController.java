package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.sessionData.SessionData;
import it.uniroma3.siw.model.User;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private SessionData sessionData;
	
	@GetMapping("/book/{id}")
	public String getBook(@PathVariable("id") Long id, Model model,Model model1) {
		User user = this.sessionData.getLoggedUser();
        model1.addAttribute("user", user);
		model.addAttribute("book", this.bookService.getBookById(id));
		return "book.html";
	}
	
	@GetMapping("/books")
	public String showBooks(Model model) {
		User loggedUser = this.sessionData.getLoggedUser();
		model.addAttribute("user", loggedUser);
		model.addAttribute("books", this.bookService.getAllBooks());
		return "books.html";
	}

}
