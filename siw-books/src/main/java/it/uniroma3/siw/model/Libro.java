package it.uniroma3.siw.model;

import java.util.HashMap;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titolo;
	private Integer annoPubblicazione;
	private HashMap<Integer, Autore> autori;
	private String urlImmagine; // una o più immagini quindi da cambiare
	
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
	
	public Integer getAnnoPubblicazione() {
		return annoPubblicazione;
	}
	public void setAnnoPubblicazione(Integer annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}
	
	public HashMap<Integer, Autore> getAutori() {
		return autori;
	}
	public void setAutori(HashMap<Integer, Autore> autori) {
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
