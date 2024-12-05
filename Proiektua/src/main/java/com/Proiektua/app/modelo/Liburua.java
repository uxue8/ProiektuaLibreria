package com.Proiektua.app.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Liburua {
	
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	 
	@Column 
	private String izenburua;
	
	@Column 
	private String egilea;
	
	@Column 
	private int stock;
	
	@ManyToOne
	private Editoriala id_editoriala;
	
	@Column 
    private double prezioa;
	
	@ManyToMany
	private List<Erabiltzaileak> erabiltzailea;
	
	
	public Liburua() {}


	public Liburua(int id, String izenburua, String egilea, int stock, double prezioa) {

		this.id = id;
		this.izenburua = izenburua;
		this.egilea = egilea;
		this.stock = stock;
		this.prezioa = prezioa;
	}


	@Override
	public String toString() {
		return "Liburua [id=" + id + ", izenburua=" + izenburua + ", egilea=" + egilea + ", stock=" + stock
				+ ", id_editoriala=" + id_editoriala + ", prezioa=" + prezioa + "]";
	}
	
	
	
	
	
	 

}
