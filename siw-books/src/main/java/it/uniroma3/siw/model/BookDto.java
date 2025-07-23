package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public class BookDto {
	
	//@NotBlank
	private String title;
	//@NotBlank
	private LocalDate year;
	//@NotBlank
	private List<Author> authors = new LinkedList<Author>();
	//@NotBlank
	private List<String> urlImages = new LinkedList<String>();
	
	
	// Getter e setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDate getYear() {
		return year;
	}
	public void setYear(LocalDate year) {
		this.year = year;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public List<String> getUrlImage() {
		return urlImages;
	}
	public void setUrlImage(List<String> urlImage) {
		this.urlImages = urlImage;
	}
	
	// Per copiare un libro esistente 
	public void copyBook(String title, LocalDate year, List<Author> authors) {
		this.setTitle(title);
    	this.setYear(year);
    	this.setAuthors(authors);
	}
	
}
