package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Credentials {
	
	public static final String DEFAULT = "UTENTE";
	public static final String ADMIN = "ADMIN";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false, unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	private String role;
	@OneToOne(cascade=CascadeType.ALL)
	private User user;
	
	public Credentials(String username, String password, String role, User user) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.user = user;
	}
	
	
	// Getter e setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	
	@Override
	public int hashCode() {
		return Objects.hash(id, password, role, username, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username)
				&& Objects.equals(user, other.user);
	}
	
	
}
