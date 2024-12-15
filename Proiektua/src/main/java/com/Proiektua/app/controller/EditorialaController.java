package com.Proiektua.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Proiektua.app.modelo.Editoriala;
import com.Proiektua.app.repository.EditorialaRepository;

@Controller
@RequestMapping("/editorialak")
public class EditorialaController {

	@Autowired
	private EditorialaRepository ediRepo;

	@GetMapping("/admin/gehitu")
	public String mostrarFormulario(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rol = userDetails.getAuthorities().stream().findFirst().map(auth -> auth.getAuthority()).orElse(null);
		model.addAttribute("rola", rol);
		if (!model.containsAttribute("editoriala")) {
			model.addAttribute("editoriala", new Editoriala());
		}
		model.addAttribute("modo", "crear");
		model.addAttribute("editorialak", ediRepo.findAll());
		return "editoriala";
	}

	@PostMapping("/admin/gorde")
	public String guardarEditoriala(@ModelAttribute Editoriala editoriala) {
		ediRepo.save(editoriala);
		return "redirect:/editorialak/admin/gehitu";
	}

	@GetMapping("/admin/editatu/{id}")
	public String editarEditoriala(@PathVariable int id, Model model) {
		Optional<Editoriala> editoriala = ediRepo.findById(id);

		model.addAttribute("editoriala", editoriala.get());
		model.addAttribute("modo", "editar");
		model.addAttribute("editorialak", ediRepo.findAll());
		return "editoriala";
	}

	@GetMapping("/admin/ezabatu/{id}")
	public String borrarEditoriala(@PathVariable int id) {
		ediRepo.deleteById(id);
		return "redirect:/editorialak/admin/gehitu";
	}

}
