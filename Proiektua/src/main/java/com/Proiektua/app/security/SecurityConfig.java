package com.Proiektua.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;

	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers( "/css/**", "/images/**", "/logina/**", "/erregistro/**", "/encriptar-contraseñas").permitAll()
				.requestMatchers("/erabiltzaileak/admin/**", "/liburuaGehitu/**", "/editorialak/admin/**", "/admin/liburuak").hasRole("ADMIN")
				.requestMatchers("/carrito", "/liburuak","/comprar").hasRole("USER")

				.anyRequest().authenticated()).formLogin(login -> login.loginPage("/logina") // Página de inicio de
																								// sesión personalizada
						.usernameParameter("email") // Configura 'email' como username
						.passwordParameter("password") // Configura el campo de contraseña
						.defaultSuccessUrl("/loginaOndo", true) // Redirección tras inicio exitoso
						.failureUrl("/logina?error=true") // Redirección tras fallo
						.permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/logina").permitAll());

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
