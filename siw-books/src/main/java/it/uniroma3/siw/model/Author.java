package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;
	private LocalDate birthDate;
	private LocalDate deathDate;
	private String nationality;
	private String urlImage;
	@ManyToMany(mappedBy="authors")
	private Set<Book> books;
	
	public Author(String name, String surname, LocalDate birthDate, LocalDate deathDate, 
			String nationality) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.nationality = nationality;
		this.urlImage = null;
	}
	
	public Author() {}
	
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
	
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	// Per aggiungere i libri all'autore
	public void addBook(Book book) {
		this.books.add(book);
	}
	
	// Per copiare un autore esistente
	public void copyAuthor(String name, String surname, LocalDate birthDate, LocalDate deathDate, String nationality) {
		this.setName(name);
		this.setSurname(surname);
		this.setBirthDate(birthDate);
		this.setDeathDate(deathDate);
		this.setNationality(nationality);
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
		Author other = (Author) obj;
		return Objects.equals(id, other.id);
	}
	
}
