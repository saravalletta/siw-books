package it.uniroma3.siw.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ReviewDto {
	
	private String title;
	@Min(1)
	@Max(5)
	private Integer score;
	private String text;
	private Long bookId;
	private Long userId;
	
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
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	// Per copiare una recensione esistente
	public void copyReview(String title, Integer score, String text, Long userId, Long bookId) {
		this.setTitle(title);
		this.setScore(score);
		this.setText(text);
		this.setUserId(userId);
		this.setBookId(bookId);
	}
	

}
