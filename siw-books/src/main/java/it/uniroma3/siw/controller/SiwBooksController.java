package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.sessionData.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiwBooksController {

    @Autowired private BookService bookService;
    @Autowired private AuthorService authorService;
    @Autowired private ReviewService reviewService;


    @GetMapping("/admin/adminHomepage")
    public String adminHomepage(){
        return "/admin/adminHomepage.html";
    }



}