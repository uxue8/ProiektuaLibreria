package com.Proiektua.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Proiektua.app.modelo.Liburua;
import java.util.List;
import com.Proiektua.app.modelo.Erabiltzaileak;


public interface LiburuaRepository extends JpaRepository<Liburua, Integer>{
	   Optional<Liburua>findById(int id);
	   List<Liburua> findByErabiltzailea(Erabiltzaileak erabiltzailea);
	}


