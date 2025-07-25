package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.BookRepository;
import jakarta.transaction.Transactional;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book createBook(String title, String description, Integer year, List<Author> authors) {
		Book book = new Book(title, description, year, authors);
		for(Author a : authors) {
			a.addBook(book);
		}
		book = this.bookRepository.save(book);
		return book;
	}
	
	public Book save(Book book) {
		return this.bookRepository.save(book);
	}
	
	@Transactional
	public void delete(Long id) {
		this.bookRepository.deleteById(id);
	}
	
	public Book getBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	public Set<Book> getAllBooks() {
		Set<Book> books = new HashSet<>(bookRepository.findAll());
		return books;
	}
	
	// Per prendere gli ultimi 10 libri inseriti
	public List<Book> getLast10Books() {
        return this.bookRepository.findTop10ByOrderByCreatedAtDesc();
    }
	
	// Per la ricerca
	public Set<Book> listAllKeyWord(String keyWord){
        if (keyWord != null){
            return this.bookRepository.findAllWithThatKeyword(keyWord);
        }
        return this.getAllBooks();
    }

}
