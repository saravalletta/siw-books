package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titolo;
	private LocalDate annoPubblicazione;
	@OneToMany
	private Set<Autore> autori;
	private String urlImmagine; // una o più immagini quindi da cambiare
	
	public Libro(String titolo, LocalDate annoPubblicazione, String urlImmagine) {
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.autori = new HashSet<Autore>();
		this.urlImmagine = urlImmagine;
	}
	
	// Getter e setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public LocalDate getAnnoPubblicazione() {
		return annoPubblicazione;
	}
	public void setAnnoPubblicazione(LocalDate annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}
	
	public Set<Autore> getAutori() {
		return autori;
	}
	public void setAutori(Set<Autore> autori) {
		this.autori = autori;
	}
	
	public String getUrlImmagine() {
		return urlImmagine;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(annoPubblicazione, autori, id, titolo, urlImmagine);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(annoPubblicazione, other.annoPubblicazione) && Objects.equals(autori, other.autori)
				&& Objects.equals(id, other.id) && Objects.equals(titolo, other.titolo)
				&& Objects.equals(urlImmagine, other.urlImmagine);
	}
}
