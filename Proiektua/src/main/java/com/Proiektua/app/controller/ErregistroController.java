package com.Proiektua.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Proiektua.app.modelo.Erabiltzaileak;
import com.Proiektua.app.repository.ErabiltzaileaRepository;


@Controller
public class ErregistroController {
	
	@Autowired
	private ErabiltzaileaRepository erabRepo;
	  @GetMapping("/erregistro")
	    public String erregistroFormulario(Model model) {
	        
	        model.addAttribute("erabiltzaileak", new Erabiltzaileak());
	        return "erregistro"; 
	    }
	  
	  @PostMapping("/erregistro/erregistratu")
	  public String erregistratu(@ModelAttribute Erabiltzaileak erabiltzailea) {
		 
		  erabRepo.save(erabiltzailea);
		  
		    return"redirect:/logina";
		    
	  }
}
