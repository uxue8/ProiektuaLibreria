package com.Proiektua.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Proiektua.app.modelo.Erabiltzaileak;
import com.Proiektua.app.repository.ErabiltzaileaRepository;

@Controller
public class LoginController {
	
	@Autowired
	private ErabiltzaileaRepository erabRepo;
	
	  @GetMapping("/logina")
	    public String logina(Model model) {
	
		  model.addAttribute("erabiltzaileak", new Erabiltzaileak());

	        model.addAttribute("loginError", false);
	        return "Logina";
	    }
	
	
	
	
	
	
	@PostMapping("/logina/sartu")	
    public String Sartu(@ModelAttribute Erabiltzaileak erabiltzailea) {
		
			System.out.println("holaaaa");
			 Optional<Erabiltzaileak> erabLogue = erabRepo.findByEmailAndPasahitza(erabiltzailea.getEmail(), erabiltzailea.getPasahitza());
			 if(erabLogue.isPresent()) {
				 return "index";
			 }
			 else {
				 return "no estas registrado";
			 }
			 
	}
}
