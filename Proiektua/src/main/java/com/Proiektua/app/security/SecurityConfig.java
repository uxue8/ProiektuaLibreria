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

   /* private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/liburuaGehitu/**", "/css/**","/images/**", "/logina/**", "/erregistro/**","/erabiltzaileak/**","/liburuak").permitAll()
                //Admin 1
               // .requestMatchers("/liburua-gehitu", "/ikusi-salmentak").hasRole("1")
                //User 0
                //.requestMatchers("/carrito").hasRole("0")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
            	    .loginPage("/logina")  // Ruta personalizada para el formulario de login
            	  //  .defaultSuccessUrl("/index", true)  // Página tras inicio de sesión exitoso
            	    //.failureUrl("/logina?error=true")  // Página tras un fallo en el login
            	    .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

  /*  @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }*/
}
