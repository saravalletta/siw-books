package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.UserDto;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

import static it.uniroma3.siw.model.Credentials.DEFAULT;

@Controller
public class AuthController {
	
	@Autowired private CredentialsService credentialsService;
	@Autowired private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/success")
	public String defaultAfterLogin() {
		
	}
	
	@GetMapping("/register")
	public String showRegisterUser(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "register.html";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult userBindingResult,
			Model model) {
		if(!userBindingResult.hasErrors()) {
			User user = this.userService.createUser(userDto.getName(), userDto.getSurname(), userDto.getEmail());
			Credentials credentials = this.credentialsService.createCredentials(userDto.getUsername(), userDto.getPassword(), DEFAULT, user);
		}
	}

}
