package it.uniroma3.siw.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.BookRepository;
import jakarta.transaction.Transactional;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book createBook(String title, String description, Integer year, List<Author> authors, List<MultipartFile> images) {
		Book book = new Book(title, description, year, authors);
		
		// Aggiungo il libro ai suoi autori
		for(Author a : authors) {
			a.addBook(book);
		}
		
		// Setto le immagini
		if (images != null && !images.isEmpty()) {
			for (String url : manageImages(images)) {
                book.addImage(url);
            }
        }
		book = this.bookRepository.save(book);
		return book;
	}
	
	public Book save(Book book) {
		return this.bookRepository.save(book);
	}
	
	@Transactional
	public void delete(Long id) {
		deleteImages(id);
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
	public List<Book> listAllKeyWord(String keyWord){
		if (keyWord == null || keyWord.trim().isEmpty()) {
            return List.of();
        }
        return this.bookRepository.searchByKeyword(keyWord);
    }
	
	// Per inserire le immagini
	private List<String> manageImages(List<MultipartFile> file) {
        Date createdAt = new Date();
        List<String> url = new ArrayList<>(file.size());

        for (MultipartFile image : file) {
            if (image.isEmpty()) continue;

            String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

            try {
                String uploadDir = "public/images/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = image.getInputStream()) {
                    Path filePath = uploadPath.resolve(storageFileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    url.add(storageFileName);
                }

            } catch (Exception ex) {
                System.out.println("Errore durante il salvataggio immagine: " + ex.getMessage());
            }
        }

        return url;
    }
	
	// Per cancellare le immagini
	private void deleteImages(Long id) {
		Book book = this.bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        List<String> images = book.getUrlImage(); // lista delle immagini
        if (images == null || images.isEmpty()) return;

        for (String url : images) {
            Path imagePath = Paths.get("public/images/" + url);

            try {
                Files.deleteIfExists(imagePath); // evita eccezioni se il file non esiste
                System.out.println("Cancellata immagine: " + url);
            } catch (IOException ex) {
                System.err.println("Errore durante la cancellazione: " + url + " - " + ex.getMessage());
            }
        }
    }

}
