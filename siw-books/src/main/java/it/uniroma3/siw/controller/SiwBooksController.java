package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.AuthorDto;
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

    
    // LIBRI
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
    		@RequestParam(name = "author", required = false) List<Long> authorsIds, BindingResult bookBindingResult,
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
    public String saveUpdatedBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult bookBindingResult, 
    		@PathVariable("id") Long id, @RequestParam(name = "author", required = false) List<Long> authorsIds, Model model) {
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
    
    // AUTORI
    @GetMapping("/addAuthor")
    public String addAuthor(Model model) {
    	AuthorDto authorDto = new AuthorDto();
    	model.addAttribute("authorDto", authorDto);
    	return "/admin/addAuthor.html";
    }
    
    @PostMapping("/addAuthor")
    public String insertAuthor(@Valid @ModelAttribute("authorDto") AuthorDto authorDto, BindingResult authorBindingResult,
    		Model model) {
    	if(!authorBindingResult.hasErrors()) {
    		Author author = this.authorService.createAuthor(authorDto.getName(), authorDto.getSurname(), authorDto.getBirthDate(), 
    				authorDto.getDeathDate(), authorDto.getNationality(), null);
    		model.addAttribute("author", author);
    		return "redirect:/author/" + author.getId();
    	}
    	else {
    		System.out.println("Errori di validazione:");
    	    authorBindingResult.getAllErrors().forEach(System.out::println);
    	    return "/admin/addAuthor.html"; 
    	}
    }
    
    @GetMapping("/updateAuthor/{id}")
    public String updateAuthor(@PathVariable("id") Long id, Model model) {
    	Author author = this.authorService.getAuthorById(id);
    	AuthorDto authorDto = new AuthorDto();
    	authorDto.copyAuthor(author.getName(), author.getSurname(), author.getBirthDate(), author.getDeathDate(), author.getNationality());
    	model.addAttribute("id", id);
    	model.addAttribute("authorDto", authorDto);
    	return "/admin/updateAuthor.html";
    }
    
    @PostMapping("/updateAuthor/{id}")
    public String saveUpdatedAuthor(@Valid @ModelAttribute("authorDto") AuthorDto authorDto, BindingResult authorBindingResult,
    		@PathVariable("id") Long id, Model model) {
    	if(!authorBindingResult.hasErrors()) {
    		Author author = this.authorService.getAuthorById(id);
        	author.copyAuthor(authorDto.getName(), authorDto.getSurname(), authorDto.getBirthDate(), authorDto.getDeathDate(), authorDto.getNationality());
        	Author updatedAuthor = this.authorService.save(author);
        	model.addAttribute("author", updatedAuthor);
        	return "redirect:/author/" + updatedAuthor.getId();
    	}
    	else {
    		System.out.println("Errori di validazione:");
    	    authorBindingResult.getAllErrors().forEach(System.out::println);
    	    return "/admin/updateBook.html"; 
    	}
    }
    
    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
    	this.authorService.delete(id);
    	return "redirect:/authors";
    }

}