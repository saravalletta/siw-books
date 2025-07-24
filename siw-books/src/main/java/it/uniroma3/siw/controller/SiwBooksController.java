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
import org.springframework.web.bind.annotation.PathVariable;
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
    		
    		Book book = this.bookService.createBook(bookDto.getTitle(), bookDto.getDescription(), bookDto.getYear(), bookDto.getAuthors());
    		model.addAttribute("book", book);
    		return "redirect:/book/" + book.getId();
    	}
    	else {
    		System.out.println("Errori di validazione:");
    	    bookBindingResult.getAllErrors().forEach(System.out::println);
    	    return "/admin/addBook.html"; 
    	}
    }
    
    @GetMapping("/updateBook/{id}")
    public String updateBook(@PathVariable("id") Long id, Model model) {
    	Book book = this.bookService.getBookById(id);
    	BookDto bookDto = new BookDto();
    	bookDto.copyBook(book.getTitle(), book.getDescription(), book.getYear(), book.getAuthors());
    	List<Author> authors = this.authorService.getAllAuthors();
    	model.addAttribute("id", id);
    	model.addAttribute("bookDto", bookDto);
    	model.addAttribute("authors", authors);
    	return "/admin/updateBook.html";
    }
    
    @PostMapping("/updateBook/{id}")
    public String saveUpdatedBook(@Valid @ModelAttribute("bookDto") BookDto bookDto,BindingResult bookBindingResult, 
    		@PathVariable("id") Long id, @RequestParam(name = "authors", required = false) List<Long> authorsIds, Model model) {
    	if(!bookBindingResult.hasErrors()) {
    		// Gestione degli autori
    		if(authorsIds != null && !authorsIds.isEmpty()) {
    			List<Author> authors = authorService.findAllById(authorsIds);
    			bookDto.setAuthors(authors);
    		}
    		
    		Book book = this.bookService.getBookById(id);
    		book.copyBook(bookDto.getTitle(), bookDto.getDescription(), bookDto.getYear(), bookDto.getAuthors());
    		Book updatedBook = this.bookService.save(book);
    		model.addAttribute("book", updatedBook);
    		return "redirect:/book/" + updatedBook.getId();
    	}
    	else {
    		System.out.println("Errori di validazione:");
    	    bookBindingResult.getAllErrors().forEach(System.out::println);
    	    return "/admin/updateBook.html"; 
    	}

    }
    
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
    	this.bookService.delete(id);
    	return "redirect:/books";
    }
    
    @GetMapping("/addAuthor")
    public String addAuthor() {
    	return "admin/addAuthor.html";
    }

}