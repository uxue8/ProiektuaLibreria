package com.Proiektua.app.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/public/**", "/about").permitAll()  // Rutas públicas
                .anyRequest().authenticated()  // Requiere autenticación para otras rutas
            )
            .formLogin(form -> form
                .loginPage("/logina")  // Página personalizada de login
                .permitAll()  // Permite acceso a la página de login sin autenticación
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  // URL para hacer logout
                .logoutSuccessUrl("/")  // Redirigir a la página principal después de logout
                .permitAll()  // Permitir logout sin autenticación
            );
        return http.build();
    }
}
