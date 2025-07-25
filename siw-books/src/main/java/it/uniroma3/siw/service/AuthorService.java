package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.AuthorRepository;
import jakarta.transaction.Transactional;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author createAuthor(String name, String surname, LocalDate birthDate, LocalDate deathDate, 
			String nationality, String urlImage) {
		Author author = new Author(name, surname, birthDate, deathDate, nationality, null);
		author = this.authorRepository.save(author);
		return author;
	}
	
	public Author save(Author author) {
		return this.authorRepository.save(author);
	}
	
	@Transactional
	public void delete(Long id) {
		this.authorRepository.deleteById(id);
	}
	
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id).orElse(null);
	}
	
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}
	
	public List<Author> findAllById(List<Long> authorsIds) {
        return authorRepository.findAllById(authorsIds);
    }
	
	// Per la ricerca
	public List<Author> listAllKeyWord(String keyWord){
        if (keyWord != null){
            return this.authorRepository.findAllWithThatKeyWord(keyWord);
        }
        return this.getAllAuthors();
    }

}
