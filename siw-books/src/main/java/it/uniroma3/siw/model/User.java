package it.uniroma3.siw.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;
	private String email;
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Review> reviews;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Credentials credentials;
	
	public User(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.reviews = new LinkedList<Review>();
	}
	
	public User() {}
	
	
	// Getter e setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}
	

}
