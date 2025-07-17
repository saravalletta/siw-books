package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.BookDto;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.sessionData.SessionData;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SiwBooksController {

    @Autowired private BookService bookService;
    @Autowired private AuthorService authorService;
    @Autowired private ReviewService reviewService;


    @GetMapping("/adminHomepage")
    public String adminHomepage(){
        return "/admin/adminHomepage.html";
    }
    
    @GetMapping("/adminBooks")
    public String adminBooks() {
    	return "/admin/adminBooks.html";
    }
    
    @GetMapping("/addBook")
    public String addBook(Model model) {
    	BookDto bookDto = new BookDto();
    	model.addAttribute("bookDto", bookDto);
    	return "/admin/addBook.html";
    }
    
    @PostMapping("/addBook")
    public String insertBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult bookBindingResult,
			Model model) {
    	if(!bookBindingResult.hasErrors()) {
    		List<Author> authors = this.authorService.getAllAuthors();
    		model.addAttribute("authors", authors);
    	}
    	
    	return "";
    }
    
    @GetMapping("/addAuthor")
    public String addAuthor() {
    	return "admin/addAuthor.html";
    }

}