package it.uniroma3.siw.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.siw.model.Credentials.ADMIN;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
			.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	protected SecurityFilterChain configure(final HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.GET, "/", "/homepage", "/login", "/register", "/css/**", "/images/**", "favicon.ico", "/books", "/book/**", "/author/**", "/authors", "/forgotPassword", "/resetPassword").permitAll()
				.requestMatchers(HttpMethod.POST, "/", "/homepage", "/login", "/register", "/css/**", "/images/**", "favicon.ico", "/books", "/book/**", "/author/**", "/authors", "/forgotPassword", "/resetPassword", "/searchBar").permitAll()
				.requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN)
				.requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN)
				.anyRequest().authenticated())
		.formLogin(form -> form
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/success", true)
				.failureUrl("/login?error=true"))
		.logout(logout -> logout
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.clearAuthentication(true)
				.permitAll());
		
		return http.build();
	}

}
