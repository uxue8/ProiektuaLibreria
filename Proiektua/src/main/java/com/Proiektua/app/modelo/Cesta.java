package com.Proiektua.app.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cesta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany
	private List<Liburua> liburu_erosita;
	
	@ElementCollection
	private List <Helbidea> helbidea;
	
	
	@Column
	private double prezio_totala;
	
	public Cesta() {
	}

	public Cesta(int id, List<Liburua> liburu_erosita, List<Helbidea> helbidea, double prezio_totala) {

		this.id = id;
		this.liburu_erosita = liburu_erosita;
		this.helbidea = helbidea;
		this.prezio_totala = prezio_totala;
	}

	@Override
	public String toString() {
		return "Cesta [id=" + id + ", liburu_erosita=" + liburu_erosita + ", helbidea=" + helbidea + ", prezio_totala="
				+ prezio_totala + "]";
	}
	
	
	
	
	
	

}
