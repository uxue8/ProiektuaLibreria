package com.Proiektua.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Proiektua.app.modelo.Cesta;
import com.Proiektua.app.modelo.Erabiltzaileak;


public interface CestaRepository extends JpaRepository<Cesta, Integer>{


	Cesta findByerabiltzailea(Erabiltzaileak erabiltzailea);
	
	
}
