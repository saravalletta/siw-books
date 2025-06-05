package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	public Utente getUtenteById(Long id) {
		return utenteRepository.findById(id).get();
	}
	
	public Iterable<Utente> getAllUtenti() {
		return utenteRepository.findAll();
	}

}
