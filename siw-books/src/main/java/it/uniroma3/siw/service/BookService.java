package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book createBook(String title, LocalDate year) {
		Book book = new Book(title, year);
		book = this.bookRepository.save(book);
		return book;
	}
	
	public Book getBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	// Per la ricerca
	public List<Book> listAllKeyWord(String keyWord){
        if (keyWord != null){
            return this.bookRepository.findAllWithThatKeyword(keyWord);
        }
        return this.getAllBooks();
    }

}
