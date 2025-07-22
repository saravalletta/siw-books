package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;

@Controller
public class SearchBarController {
	
	@Autowired private BookService bookService;
	@Autowired private AuthorService authorService; 
	
	@PostMapping("/searchBar")
    public String searchBar(Model model, @Param("keyword") String keyword) {
        List<Book> books = this.bookService.listAllKeyWord(keyword);
        List<Author> authors = this.authorService.listAll(keyword);
        model.addAttribute("books", books);
        model.addAttribute("autori", authors);
        model.addAttribute("keyword", keyword);
        return "searchBar.html";
    }


}
