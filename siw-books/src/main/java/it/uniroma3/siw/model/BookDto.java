package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public class BookDto {
	
	@NotBlank
	private String title;
	@NotBlank
	private LocalDate year;
	@NotBlank
	private Set<Long> authorsIds = new HashSet<Long>();
	@NotBlank
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
	
	public Set<Long> getAuthorsIds() {
		return authorsIds;
	}
	public void setAuthorsIds(Set<Long> authorsIds) {
		this.authorsIds = authorsIds;
	}
	
	public List<String> getUrlImage() {
		return urlImages;
	}
	public void setUrlImage(List<String> urlImage) {
		this.urlImages = urlImage;
	}
	
}
