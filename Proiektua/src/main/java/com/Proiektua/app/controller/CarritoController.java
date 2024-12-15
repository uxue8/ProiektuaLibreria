package com.Proiektua.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Proiektua.app.modelo.Cesta;
import com.Proiektua.app.modelo.Erabiltzaileak;
import com.Proiektua.app.modelo.Helbidea;
import com.Proiektua.app.modelo.Liburua;
import com.Proiektua.app.repository.CestaRepository;
import com.Proiektua.app.repository.ErabiltzaileaRepository;
import com.Proiektua.app.repository.LiburuaRepository;

@Controller
public class CarritoController {

	@Autowired
	private CestaRepository cesRepo;

	@Autowired
	private ErabiltzaileaRepository erabRepo;
	@Autowired
	private LiburuaRepository libuRepo;

	@GetMapping("/carrito")
	public String mostrarCarrito(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rol = userDetails.getAuthorities().stream().findFirst().map(auth -> auth.getAuthority()).orElse(null);
		model.addAttribute("rola", rol);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Erabiltzaileak erabiltzailea = erabRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		Cesta cestak = cesRepo.findByerabiltzailea(erabiltzailea);

		System.out.println(cestak);

		model.addAttribute("cestak", cestak);
		model.addAttribute("helbideak", cestak.getHelbidea());

		model.addAttribute("helbidea", new Helbidea());

		return "carrito";
	}

	@GetMapping("/carrito/vaciar")
	public String vaciarCarrito() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Erabiltzaileak erabiltzailea = erabRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		Cesta cesta = cesRepo.findByerabiltzailea(erabiltzailea);
		List<Liburua> liburuGuztia = libuRepo.findByErabiltzailea(erabiltzailea);
		for (Liburua liburua : liburuGuztia) {
			
			liburua.getErabiltzailea().remove(erabiltzailea);
			libuRepo.save(liburua);
		}
		cesta.getLiburu_erosita().clear();
		cesta.setPrezio_totala(0);
		
		cesRepo.save(cesta);
		
		
		return "redirect:/carrito";
	}

	@PostMapping("/guardarDireccion")
	public String guardarDireccion(@ModelAttribute Helbidea helbidea) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Erabiltzaileak erabiltzailea = erabRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		Cesta cesta = cesRepo.findByerabiltzailea(erabiltzailea);

		cesta.getHelbidea().add(helbidea);
		cesRepo.save(cesta);

		return "redirect:/carrito"; // Redirigir al carrito
	}
}
