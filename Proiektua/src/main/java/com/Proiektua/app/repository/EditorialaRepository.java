package com.Proiektua.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Proiektua.app.modelo.Editoriala;


public interface EditorialaRepository extends JpaRepository<Editoriala, Integer>{

	List<Editoriala> findAll();
	
}

