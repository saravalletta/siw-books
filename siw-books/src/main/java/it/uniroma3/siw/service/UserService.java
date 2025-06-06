package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
public class UserService {
	
	@Autowired
	private UtenteRepository userRepository;
	
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

}
