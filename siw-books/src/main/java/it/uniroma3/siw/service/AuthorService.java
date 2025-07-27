package it.uniroma3.siw.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.AuthorRepository;
import jakarta.transaction.Transactional;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author createAuthor(String name, String surname, LocalDate birthDate, LocalDate deathDate, 
			String nationality, MultipartFile image) {
		Author author = new Author(name, surname, birthDate, deathDate, nationality);
		author.setUrlImage(this.addImage(image));
		author = this.authorRepository.save(author);
		return author;
	}
	
	public Author save(Author author) {
		return this.authorRepository.save(author);
	}
	
	@Transactional
	public void delete(Long id) {
		Author author = this.authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author not found"));
		
		// Elimino l'autore dalle liste dei libri che ha scritto
		for(Book b : author.getBooks()) {
			b.getAuthors().remove(author);
		}
		author.getBooks().clear();
		this.deleteImage(author);
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
	
	// Per la gestione delle foto
	public String addImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File non valido o vuoto");
        }
        MultipartFile image = file;
        Date createdAt = new Date();

        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";    //directory dove salvare le immagini
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return storageFileName;
    }

    private void deleteImage(Author author) {
        try {
            Path imagePath = Paths.get("public/images/" + author.getUrlImage());
            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
