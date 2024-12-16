package com.Proiektua.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Proiektua.app.modelo.Cesta;
import com.Proiektua.app.modelo.Editoriala;
import com.Proiektua.app.modelo.Erabiltzaileak;
import com.Proiektua.app.modelo.Liburua;
import com.Proiektua.app.repository.CestaRepository;
import com.Proiektua.app.repository.EditorialaRepository;
import com.Proiektua.app.repository.ErabiltzaileaRepository;
import com.Proiektua.app.repository.LiburuaRepository;

@Controller
public class LiburuaController {

	@Autowired
	private LiburuaRepository libuRepo;

	@Autowired
	private EditorialaRepository ediRepo;

	@Autowired
	private CestaRepository cesRepo;
	@Autowired
	private ErabiltzaileaRepository erabRepo;

	@GetMapping("/liburuak")
	public String liburuIkusi(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rol = userDetails.getAuthorities().stream().findFirst().map(auth -> auth.getAuthority()).orElse(null);
		model.addAttribute("rola", rol);
		List<Liburua> erabiltzaileakList = libuRepo.findAll();
		model.addAttribute("liburuak", erabiltzaileakList);

		return "liburuak";

	}

	@GetMapping("/admin/liburuak")
	public String liburuakIkusiAdmin(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rol = userDetails.getAuthorities().stream().findFirst().map(auth -> auth.getAuthority()).orElse(null);
		model.addAttribute("rola", rol);
		model.addAttribute("liburuak", libuRepo.findAll());
		return "LiburuaAdmin";
	}

	@GetMapping("/liburuaGehitu")
	public String LiburuaForm(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rol = userDetails.getAuthorities().stream().findFirst().map(auth -> auth.getAuthority()).orElse(null);
		model.addAttribute("rola", rol);
		List<Editoriala> editoriales = ediRepo.findAll();
		model.addAttribute("editoriales", editoriales);
		model.addAttribute("liburua", new Liburua());
		return "LiburuGehitu";
	}

	@PostMapping("/liburuaGehitu/add")
	public String gehitu(@ModelAttribute("liburua") Liburua liburua, @RequestParam("file") MultipartFile irudia) {

		try {
			if (!irudia.isEmpty()) {
				// Ruta del directorio donde guardar las imágenes
				Path directorioImagen = Paths.get("src//main//resources//static/images");
				String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();

				// Guardar archivo en el directorio
				byte[] bytesImg = irudia.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + irudia.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				// Asignar el nombre del archivo al atributo 'irudia' en el modelo
				liburua.setIrudia(irudia.getOriginalFilename());
			}

			// Guardar el objeto liburua en el repositorio
			libuRepo.save(liburua);
			return "redirect:/admin/liburuak"; // Redireccionar después de guardar
		} catch (IOException e) {
			e.printStackTrace();
			return "error"; // Si ocurre un error, mostramos una página de error
		}

	}

	@PostMapping("/comprar")
	public String comprar(@ModelAttribute("liburua") Liburua liburua, @RequestParam Long id,
			@RequestParam int cantidad) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		Erabiltzaileak erabiltzailea = erabRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		Optional<Liburua> libu = libuRepo.findById(liburua.getId());

		Cesta cesEncontrada = cesRepo.findByerabiltzailea(erabiltzailea);

		Double precioFinal = libu.get().getPrezioa();

		System.out.println(precioFinal);

		if (cantidad > 1) {
			precioFinal = precioFinal * cantidad;
			for (int i = 0; i < cantidad; i++) {
				cesEncontrada.getLiburu_erosita().add(libu.get());
				libu.get().getErabiltzailea().add(erabiltzailea);
			}

			cesEncontrada.setPrezio_totala(cesEncontrada.getPrezio_totala() + precioFinal);

			cesRepo.save(cesEncontrada);

		} else {

			cesEncontrada.getLiburu_erosita().add(libu.get());
			cesEncontrada.setPrezio_totala(cesEncontrada.getPrezio_totala() + precioFinal);

			libu.get().getErabiltzailea().add(erabiltzailea);
			cesRepo.save(cesEncontrada);
		}

		return "redirect:/liburuak";
	}

	@GetMapping("/liburua/editatu/{id}")
	public String liburuaEditatuAdmin(@PathVariable int id, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rol = userDetails.getAuthorities().stream().findFirst().map(auth -> auth.getAuthority()).orElse(null);
		model.addAttribute("rola", rol);
		Optional<Liburua> liburu = libuRepo.findById(id);
		model.addAttribute("liburua", liburu.get());
		model.addAttribute("id_editoriala", liburu.get().getId_editoriala());
		model.addAttribute("editoriales", ediRepo.findAll());
		return "LiburuGehitu";
	}

	@GetMapping("/liburua/ezabatu/{id}")
	public String liburuaEzabatuAdmin(@PathVariable int id, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String rol = userDetails.getAuthorities().stream().findFirst().map(auth -> auth.getAuthority()).orElse(null);
		model.addAttribute("rola", rol);

		List<Liburua> libu = libuRepo.findAll();
		List<Cesta> cesta = cesRepo.findAll();

		for (Liburua liburua : libu) {
			if (liburua.getId() == id) {
				liburua.getErabiltzailea().clear();

		 		for (Cesta cesta2 : cesta) {
					List<Liburua> librosAEliminar = new ArrayList<>();
					for (Liburua libroEnCesta : cesta2.getLiburu_erosita()) {
						if (libroEnCesta.equals(liburua)) {
							librosAEliminar.add(libroEnCesta);
							cesta2.setPrezio_totala(cesta2.getPrezio_totala() - liburua.getPrezioa());
						}
					}
					cesta2.getLiburu_erosita().removeAll(librosAEliminar);
				}
			}
		}

		libuRepo.deleteById(id);

		return "redirect:/admin/liburuak";
	}

}
