package com.Proiektua.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Proiektua.app.modelo.Erabiltzaileak;
import java.util.List;
import java.util.Optional;

public interface ErabiltzaileaRepository extends JpaRepository<Erabiltzaileak, Integer>{
	 Optional<Erabiltzaileak> findByEmailAndPasahitza(String email, String pasahitza);
	 Optional <Erabiltzaileak> findByEmail(String email);
	 
}
