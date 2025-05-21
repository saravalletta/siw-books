package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	private java.time.LocalDate dataDiNascita;
	private java.time.LocalDate dataDiMorte;
	private String nazionalità;
	private String urlImmagine;
	
	// Getter e setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public java.time.LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(java.time.LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public java.time.LocalDate getDataDiMorte() {
		return dataDiMorte;
	}
	public void setDataDiMorte(java.time.LocalDate dataDiMorte) {
		this.dataDiMorte = dataDiMorte;
	}
	
	public String getNazionalità() {
		return nazionalità;
	}
	public void setNazionalità(String nazionalità) {
		this.nazionalità = nazionalità;
	}
	
	public String getUrlImmagine() {
		return urlImmagine;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataDiMorte, dataDiNascita, id, nazionalità, nome, urlImmagine);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autore other = (Autore) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataDiMorte, other.dataDiMorte)
				&& Objects.equals(dataDiNascita, other.dataDiNascita) && Objects.equals(id, other.id)
				&& Objects.equals(nazionalità, other.nazionalità) && Objects.equals(nome, other.nome)
				&& Objects.equals(urlImmagine, other.urlImmagine);
	}
}
