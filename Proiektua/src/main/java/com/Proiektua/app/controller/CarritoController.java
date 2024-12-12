package com.Proiektua.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Proiektua.app.modelo.Cesta;
import com.Proiektua.app.modelo.Liburua;
import com.Proiektua.app.repository.CestaRepository;


@Controller
public class CarritoController {
	
	@Autowired
	private CestaRepository cesRepo;

	   @GetMapping("/carrito")
	    public String mostrarErabiltzaileak(Model model) {
	        List<Cesta> cestak = cesRepo.findAll();
	        model.addAttribute("cesta", cestak);
	     
	        return "carrito";  
	    }
}
