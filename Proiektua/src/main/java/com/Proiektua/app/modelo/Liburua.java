package com.Proiektua.app.modelo;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	@Column
    private String irudia;
	
	public Liburua() {}




	
	public Liburua(int id, String izenburua, String egilea, int stock, Editoriala id_editoriala, double prezioa,
			String irudia) {
		this.id = id;
		this.izenburua = izenburua;
		this.egilea = egilea;
		this.stock = stock;
		this.id_editoriala = id_editoriala;
		this.prezioa = prezioa;
		this.irudia =  irudia;
	}
	
	@Override
	public String toString() {
		return "Liburua [id=" + id + ", izenburua=" + izenburua + ", egilea=" + egilea + ", stock=" + stock
				+ ", id_editoriala=" + id_editoriala + ", prezioa=" + prezioa + "]";
	}


	
	
	
	
	
	 

}
