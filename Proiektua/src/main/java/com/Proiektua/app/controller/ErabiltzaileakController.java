package com.Proiektua.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Proiektua.app.modelo.Erabiltzaileak;
import com.Proiektua.app.repository.ErabiltzaileaRepository;

@Controller
public class ErabiltzaileakController {
	
	
	 @Autowired
	    private ErabiltzaileaRepository erabRepo;

	    @GetMapping("/erabiltzaileak")
	    public String mostrarErabiltzaileak(Model model) {
	        List<Erabiltzaileak> erabiltzaileakList = erabRepo.findAll();
	        model.addAttribute("erabiltzaileak", erabiltzaileakList);
	        return "erabiltzaileakIkusi";  
	    }
	    
	    
	    // Editar 
	    @GetMapping("/erabiltzaileak/edit/{id}")
	    public String editatuErab(@PathVariable int id,Model model){
	    	
	    	
	    	model.addAttribute("erabiltzailea",  erabRepo.findById(id));
	    	return "FormErabiltzaile";
	    	
	    }
	    
	    //ezabatu
	   
	    @GetMapping("/erabiltzaileak/delete/{id}")
	    public String ezabatuErab(@PathVariable int id,Model model) {
	    	     erabRepo.deleteById(id);
	    	     return"redirect:/erabiltzaileak";
	    }


}
