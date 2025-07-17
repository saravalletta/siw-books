package it.uniroma3.siw.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
	
    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        // Ottieni l'oggetto Authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Determina se l'utente è loggato
        boolean isAnonymous = auth == null || auth instanceof AnonymousAuthenticationToken;

        // Aggiungi l'attributo globale
        model.addAttribute("isAnonymous", isAnonymous);

        // Se l'utente non è anonimo, puoi aggiungere altre informazioni
        if (!isAnonymous) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails userDetails) {

                boolean isAdmin = auth.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().trim().equals("ADMIN"));
                model.addAttribute("isAdmin", isAdmin);
            }
        } else {
            model.addAttribute("isAdmin", false);
        }
    }
}
