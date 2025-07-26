package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	@Column(length = 1000)
	private String description;
	private Integer year;
	@ManyToMany
	private List<Author> authors;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "book_images", joinColumns = @JoinColumn(name = "book_id"))
	private List<String> urlImage;
	@OneToMany
	private List<Review> reviews;
	
	// Per gestire gli ultimi libri inseriti
	@CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
	
	public Book(String title, String description, Integer year, List<Author> authors) {
		this.title = title;
		this.description = description;
		this.year = year;
		this.authors = authors;
		this.urlImage = new LinkedList<String>();
		this.reviews = new LinkedList<Review>();
	}
	
	public Book() {}
	
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public List<String> getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(List<String> urlImage) {
		this.urlImage = urlImage;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void addImage(String img){
        this.urlImage.add(img);
    }
	
	// Per copiare un libro esistente 
	public void copyBook(String title, String description, Integer year, List<Author> authors) {
		this.setTitle(title);
		this.setDescription(description);
    	this.setYear(year);
    	this.setAuthors(authors);
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
	
}
