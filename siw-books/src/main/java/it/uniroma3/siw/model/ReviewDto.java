package it.uniroma3.siw.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ReviewDto {
	
	private String title;
	@Min(1)
	@Max(5)
	private Integer score;
	private String text;
	private User user;
	private Book book;
	
	// Getter e setter
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
	
	// Per copiare una recensione esistente
	public void copyReview(String title, Integer score, String text, User user, Book book) {
		this.setTitle(title);
		this.setScore(score);
		this.setText(text);
		this.setUser(user);
		this.setBook(book);
	}
	

}
