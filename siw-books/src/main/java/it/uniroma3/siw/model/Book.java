package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private LocalDate year;
	@OneToMany
	private Set<Author> authors;
	@ElementCollection
	private List<String> urlImage; // una o più immagini quindi da cambiare
	
	public Book(String title, LocalDate year) {
		this.title = title;
		this.year = year;
		this.authors = new HashSet<Author>();
		this.urlImage = new LinkedList<String>();
	}
	
	// Getter e setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(HashSet<Author> authors) {
		this.authors = authors;
	}
	
	public List<String> getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(LinkedList<String> urlImage) {
		this.urlImage = urlImage;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(year, authors, id, title, urlImage);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(year, other.year) && Objects.equals(authors, other.authors)
				&& Objects.equals(id, other.id) && Objects.equals(title, other.title)
				&& Objects.equals(urlImage, other.urlImage);
	}
}
