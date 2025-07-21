package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired private UserRepository userRepository;
	@Autowired private CredentialsRepository credentialsRepository;
	
	public User createUser(String name, String surname, String email) {
		User user = new User(name, surname, email);
		user = this.userRepository.save(user);
		return user;
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	// Per prendere l'utente tramite l'email
	@Transactional
	public User getUser(String email) {
		Optional<User> user = this.userRepository.findByEmail(email);
		return user.orElse(null);
	}
	
	// Metodi per il reset password
	@Transactional
	public void updateResetPassword(String email, String token) throws UserNotFoundException {
		User user = this.getUser(email);
		if (user != null) {
			Credentials credentials = user.getCredentials();
			credentials.setResetPasswordToken(token);
			this.credentialsRepository.save(credentials);
		} else {
			throw new UserNotFoundException("non abbiamo trovato alcun credenziali associata a: " + email);
		}
	}

	@Transactional
	public User getByResetPasswordToken(String resetPasswordToken) {
		Credentials credentials = this.credentialsRepository.findByResetPasswordToken(resetPasswordToken).orElse(null);
		User user = credentials.getUser();
		return user;
	}

	@Transactional
	public void updatePassword(String email, String newPassword) throws UserNotFoundException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePassword = encoder.encode(newPassword);
		User user = this.getUser(email);
		if (user != null) {
			Credentials credentials = user.getCredentials();
			credentials.setPassword(encodePassword);
			credentials.setResetPasswordToken(null);
			this.credentialsRepository.save(credentials);
		} else {
			throw new UserNotFoundException("non abbiamo trovato alcun credenziali associata a: " + email);
		}
	}

}
