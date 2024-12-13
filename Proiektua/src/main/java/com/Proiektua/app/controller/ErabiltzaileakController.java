package com.Proiektua.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Proiektua.app.modelo.Erabiltzaileak;
import com.Proiektua.app.repository.ErabiltzaileaRepository;

@Controller
@RequestMapping("/erabiltzaileak")
public class ErabiltzaileakController {
	
	
	 @Autowired
	    private ErabiltzaileaRepository erabRepo;

	    @GetMapping("/admin/ikusi")
	    public String mostrarErabiltzaileak(Model model) {
	        List<Erabiltzaileak> erabiltzaileakList = erabRepo.findAll();
	        model.addAttribute("erabiltzaileak", erabiltzaileakList);
	        return "erabiltzaileakIkusi";  
	    }
	    
	    
	    // Editar 
	    @GetMapping("/admin/edit/{id}")
	    public String editatuErab(@PathVariable int id,Model model){
	    	
	    	
	    	model.addAttribute("erabiltzailea",  erabRepo.findById(id));
	    	return "FormErabiltzaile";
	    	
	    }
	    
	    //ezabatu
	   
	    @GetMapping("/admin/delete/{id}")
	    public String ezabatuErab(@PathVariable int id,Model model) {
	    	     erabRepo.deleteById(id);
	    	     return"redirect:/erabiltzaileak";
	    }

	    @GetMapping("/perfila/ikusi")
	    public String ikusiPerfila(Model model) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String rol = userDetails.getAuthorities().stream().findFirst().map(auth -> auth.getAuthority()).orElse(null);
			model.addAttribute("rola", rol);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email = auth.getName();
			Optional<Erabiltzaileak> erab = erabRepo.findByEmail(email);
			model.addAttribute("erabiltzailea", erab);
			model.addAttribute("tipo", "ikusi");
			return "FormErabiltzaile";
	    }
	    
	    @PostMapping("/perfila/ikusi")
	    public String perfilaIkusi(@ModelAttribute("erabiltzailea") Erabiltzaileak erab) {
	    	erabRepo.save(erab);
	    	return "redirect:/loginaOndo";
	    }
}
