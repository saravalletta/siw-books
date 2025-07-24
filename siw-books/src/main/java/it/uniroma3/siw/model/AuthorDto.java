package it.uniroma3.siw.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class AuthorDto {
	
	//@NotBlank
	private String name;
	//@NotBlank
	private String surname;
	//@NotBlank
	private LocalDate birthDate;
	//@NotBlank
	private LocalDate deathDate;
	//@NotBlank
	private String nationality;
	//@NotBlank
	private String urlImage;
	
	// Getter e setter
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
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public LocalDate getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(LocalDate deathDate) {
		this.deathDate = deathDate;
	}
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	// Per copiare un autore esistente
	public void copyAuthor(String name, String surname, LocalDate birthDate, LocalDate deathDate, String nationality) {
		this.setName(name);
		this.setSurname(surname);
		this.setBirthDate(birthDate);
		this.setDeathDate(deathDate);
		this.setNationality(nationality);
	}

}
