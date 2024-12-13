package com.Proiektua.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Proiektua.app.modelo.Cesta;
import com.Proiektua.app.modelo.Helbidea;
import com.Proiektua.app.repository.CestaRepository;


@Controller
public class CarritoController {
	
	@Autowired
	private CestaRepository cesRepo;

	   @GetMapping("/carrito")
	    public String mostrarErabiltzaileak(Model model) {
	     List<Cesta> cestak = cesRepo.findAll();
	         
	         for(Cesta ce : cestak) {
	        	 
	            System.out.println(ce);
	         }
	       
	        model.addAttribute("cestak", cestak);
	        
	        model.addAttribute("helbidea",new Helbidea());

	     
	        
	     
	        return "carrito";  
	    }
}
