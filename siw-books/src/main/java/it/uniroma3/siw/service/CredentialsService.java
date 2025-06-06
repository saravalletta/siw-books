package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredenzialiRepository;

@Service
public class CredentialsService {
	
	@Autowired
	private CredenzialiRepository credenzialiRepository;
	
	public Credentials getCredenzialiById(Long id) {
		return credenzialiRepository.findById(id).orElse(null);
	}
	
	public Credentials getCredenzialiByUsername(String username) {
		return credenzialiRepository.findByUsername(username).orElse(null);
	}
	
	public Iterable<Credentials> getAllCredenziali() {
		return credenzialiRepository.findAll();
	}

}
