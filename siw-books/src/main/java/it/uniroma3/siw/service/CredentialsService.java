package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredenzialiRepository;

@Service
public class CredentialsService {
	
	@Autowired
	private CredenzialiRepository credentialsRepository;
	
	public Credentials getCredentialsById(Long id) {
		return credentialsRepository.findById(id).orElse(null);
	}
	
	public Credentials getCredentialsByUsername(String username) {
		return credentialsRepository.findByUsername(username).orElse(null);
	}
	
	public Iterable<Credentials> getAllCredentials() {
		return credentialsRepository.findAll();
	}

}
