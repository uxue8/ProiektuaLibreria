package com.Proiektua.app.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cesta_liburua", joinColumns = @JoinColumn(name = "cesta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))

	private List<Liburua> liburu_erosita;

	@ElementCollection
	private List<Helbidea> helbidea = new ArrayList<>();

	@Column
	private double prezio_totala;

	@OneToOne
	@JoinColumn(name = "erabiltzailea_id")
	private Erabiltzaileak erabiltzailea;

	public Cesta() {
	}

	public Cesta(int id, ArrayList<Liburua> liburu_erosita, List<Helbidea> helbidea, double prezio_totala) {

		this.id = id;
		this.liburu_erosita = liburu_erosita;
		this.helbidea = helbidea;
		this.prezio_totala = prezio_totala;
	}

	@Override
	public String toString() {
		return "Cesta{" + "id=" + id + ", liburu_erosita="
				+ (liburu_erosita != null ? liburu_erosita.size() + " libros" : "null") + ", prezio_totala="
				+ prezio_totala + '}';
	}

	public Cesta(int id, ArrayList<Liburua> liburu_erosita) {

		this.id = id;
		this.liburu_erosita = liburu_erosita;
	}

}
