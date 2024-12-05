package com.Proiektua.app.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Erabiltzaileak {
	
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		@Column
		private String izena;
		@Column
		private String abizena;
		@Column
		private String email;
		@Column
		private String pasahitza;
		@Column
		private String tel_zenbakia;
		@Embedded
		private Helbidea helbidea;
		@Column
		private int admin;
		
		
		public Erabiltzaileak() {}


		public Erabiltzaileak(int id, String izena, String abizena, String email, String pasahitza,
				String tel_zenbakia, int admin) {
			this.id = id;
			this.izena = izena;
			this.abizena = abizena;
			this.email = email;
			this.pasahitza = pasahitza;
			this.tel_zenbakia = tel_zenbakia;
			this.admin=admin;
		}


		@Override
		public String toString() {
			return "Erabiltzaileak [id=" + id + ", izena=" + izena + ", abizena=" + abizena + ", email=" + email
					+ ", pasahitza=" + pasahitza + ", tel_zenbakia=" + tel_zenbakia + ", helbidea=" + helbidea + "]";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
