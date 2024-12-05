package com.Proiektua.app.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Editoriala {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	 
	 @Column 
	 private String izena;
	 @Column 
	 private String herrialdea;
	 
	 @OneToMany(mappedBy = "egilea", cascade = CascadeType.ALL)
	 private List<Liburua> liburuak;

	 
	 
	public Editoriala() {}
	 
	public Editoriala(int id, String izena, String herrialdea) {
		this.id = id;
		this.izena = izena;
		this.herrialdea = herrialdea;
	}

	@Override
	public String toString() {
		return "Editoriala [id=" + id + ", izena=" + izena + ", herrialdea=" + herrialdea + ", liburuak=" + liburuak
				+ "]";
	}
	
	
	
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	
}
