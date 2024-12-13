package com.Proiektua.app.security;



import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Proiektua.app.modelo.Erabiltzaileak;
import com.Proiektua.app.repository.ErabiltzaileaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ErabiltzaileaRepository erabRepo;

    public UserDetailsServiceImpl(ErabiltzaileaRepository erabRepo) {
        this.erabRepo = erabRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
   
        Optional<Erabiltzaileak> userOpt = erabRepo.findByEmail(username);
       
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + username);
        }

        Erabiltzaileak erabiltzailea = userOpt.get();
       
        // Crea un SimpleGrantedAuthority para el rol del usuario
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Integer.toString(erabiltzailea.getAdmin()));

        System.out.println(authority);
       
        return new User(
                erabiltzailea.getEmail(),   // Email como nombre de usuario
                erabiltzailea.getPasahitza(),  // Contraseña encriptada
                Collections.singletonList(authority)  // Lista de roles/authorities
        );
    }
}
