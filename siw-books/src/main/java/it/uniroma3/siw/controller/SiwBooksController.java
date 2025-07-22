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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiwBooksController {

    @Autowired private BookService bookService;
    @Autowired private AuthorService authorService;

    
    @GetMapping("/addBook")
    public String addBook(Model model) {
    	BookDto bookDto = new BookDto();
    	List<Author> authors = this.authorService.getAllAuthors();
    	model.addAttribute("bookDto", bookDto);
    	model.addAttribute("authors", authors);
    	return "/admin/addBook.html";
    }
    
    @PostMapping("/addBook")
    public String insertBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, 
    		@RequestParam(name = "authors", required = false) List<Long> authorsIds, BindingResult bookBindingResult,
			Model model) {
    	if(!bookBindingResult.hasErrors()) {
    		 // Gestione degli autori
    		if(authorsIds != null && !authorsIds.isEmpty()) {
    			List<Author> authors = authorService.findAllById(authorsIds);
    			bookDto.setAuthors(authors);
    		}
    		
    		Book book = this.bookService.createBook(bookDto.getTitle(), bookDto.getYear(), bookDto.getAuthors());
    		model.addAttribute("book", book);
    		return "redirect:/book/" + book.getId();
    	}
    	else {
    		List<Author> authors = this.authorService.getAllAuthors();
        	model.addAttribute("bookDto", bookDto);
        	model.addAttribute("authors", authors);
        	return "/admin/addBook.html";
    	}
    }
    
    @GetMapping("/addAuthor")
    public String addAuthor() {
    	return "admin/addAuthor.html";
    }

}