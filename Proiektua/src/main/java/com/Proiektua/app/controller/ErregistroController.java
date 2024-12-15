package com.Proiektua.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Proiektua.app.modelo.Cesta;
import com.Proiektua.app.modelo.Erabiltzaileak;
import com.Proiektua.app.modelo.Helbidea;
import com.Proiektua.app.repository.CestaRepository;
import com.Proiektua.app.repository.ErabiltzaileaRepository;

@Controller
public class ErregistroController {

	@Autowired
	private ErabiltzaileaRepository erabRepo;
	@Autowired
	private CestaRepository cestaRepo;
	@Autowired
	private PasswordEncoder passwordencoder;

	@GetMapping("/erregistro")
	public String erregistroFormulario(Model model) {
		model.addAttribute("tipo", "erregistro");
		model.addAttribute("erabiltzailea", new Erabiltzaileak());
		return "FormErabiltzaile";
	}

	@PostMapping("/erregistro/erregistratu")
	public String erregistratu(@ModelAttribute Erabiltzaileak erabiltzailea) {
		erabiltzailea.setPasahitza(passwordencoder.encode(erabiltzailea.getPasahitza()));
		erabiltzailea.setAdmin(0);
		erabRepo.save(erabiltzailea);
		List<Helbidea> helbideLista = new ArrayList<>();
		helbideLista.add(erabiltzailea.getHelbidea());
		Cesta cestaBerria = new Cesta();
		cestaBerria.setErabiltzailea(erabiltzailea);
		cestaBerria.setHelbidea(helbideLista);
		cestaBerria.setPrezio_totala(0);
		cestaRepo.save(cestaBerria);
		return "redirect:/logina";

	}
}
