/*package com.Proiektua.app.security;



import java.util.Collection;
import java.util.Collections;

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

    @Autowired
    private ErabiltzaileaRepository erabiltzaileaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Erabiltzaileak erabiltzailea = erabiltzaileaRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Collection<GrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + String.valueOf(erabiltzailea.getAdmin()))
        );

        return new User(erabiltzailea.getEmail(), erabiltzailea.getPasahitza(), authorities);
    }


}*/
