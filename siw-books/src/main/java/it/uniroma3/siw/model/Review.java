package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	@Min(1)
	@Max(5)
	private Integer score;
	@Column(length = 1000)
	private String text;
	@ManyToOne
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(nullable = false, name = "book_id")
	private Book book;
	
	// Per gestire le ultime recensioni inserite
	@CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
	
	public Review(String title, Integer score, String text, User user, Book book) {
		this.title = title;
		this.score = score;
		this.text = text;
		this.user = user;
		this.book = book;
	}
	
	public Review() {}
	
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
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // Per copiare una recensione esistente
 	public void copyReview(String title, Integer score, String text, User user, Book book) {
 		this.setTitle(title);
 		this.setScore(score);
 		this.setText(text);
 		this.setUser(user);
 		this.setBook(book);
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
		Review other = (Review) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
