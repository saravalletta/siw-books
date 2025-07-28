package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.BookService;
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
	
	@GetMapping("/book/{id}")
	public String getBook(@PathVariable("id") Long id, Model model) {
		Book book = this.bookService.getBookById(id);
		List<Review> reviews = book.getReviews();
		User user = this.sessionData.getLoggedUser();
		model.addAttribute("book", book);
		model.addAttribute("reviews", reviews);
		if(user != null) {
			model.addAttribute("userId", user.getId());
		}
		return "book.html";
	}
	
	@GetMapping("/books")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.getAllBooks());
		model.addAttribute("bestsellers", this.bookService.getLast10Books());
		return "books.html";
	}

}
