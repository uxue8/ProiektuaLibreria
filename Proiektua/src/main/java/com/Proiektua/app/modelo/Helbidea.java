package com.Proiektua.app.modelo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Helbidea {
	 private String kalea;
	 private String hiria;
	 private String posta_kodea;
	 
	 
	public Helbidea() {}
	public Helbidea(String kalea, String hiria, String posta_kodea) {
		
		this.kalea = kalea;
		this.hiria = hiria;
		this.posta_kodea = posta_kodea;
	}


    
	@Override
	public String toString() {
		return "Helbidea [kalea=" + kalea + ", hiria=" + hiria + ", posta_kodea=" + posta_kodea + "]";
	}



	
	
	 
	 
	 
}
