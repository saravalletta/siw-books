package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
	@Autowired
	private CredentialsRepository credentialsRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Credentials getCredentialsById(Long id) {
		return credentialsRepository.findById(id).orElse(null);
	}
	
	public Credentials getCredentialsByUsername(String username) {
		return credentialsRepository.findByUsername(username).orElse(null);
	}
	
	public Iterable<Credentials> getAllCredentials() {
		return credentialsRepository.findAll();
	}
	
	public Credentials createCredentials(String username, String password, String role, User user) {
		String cryptedPassword = this.passwordEncoder.encode(password);
		Credentials credentials = new Credentials(username, cryptedPassword, role, user);
		credentials = this.credentialsRepository.save(credentials);
		return credentials;
	}

}
