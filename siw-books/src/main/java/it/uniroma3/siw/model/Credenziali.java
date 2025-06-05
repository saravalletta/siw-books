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
public class Credenziali {
	
	public static final String DEFAULT_ROLE = "UTENTE";
	public static final String ADMIN_ROLE = "ADMIN";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false, unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	private String ruolo;
	@OneToOne(cascade=CascadeType.ALL)
	private Utente utente;
	
	public Credenziali(String username, String password, String ruolo, Utente utente) {
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
		this.utente = utente;
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
	
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}


	
	@Override
	public int hashCode() {
		return Objects.hash(id, password, ruolo, username, utente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credenziali other = (Credenziali) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(ruolo, other.ruolo) && Objects.equals(username, other.username)
				&& Objects.equals(utente, other.utente);
	}
	
	
}
