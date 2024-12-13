package com.Proiektua.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/logina")
	public String logina(Model model) {

		model.addAttribute("erabiltzaileak", new Erabiltzaileak());

		model.addAttribute("loginError", false);
		return "Logina";
	}

	@GetMapping("/encriptar-contraseñas")
	public String encriptarContraseñas() {
		// Obtener todos los usuarios
		List<Erabiltzaileak> usuarios = erabRepo.findAll();

		for (Erabiltzaileak usuario : usuarios) {
			// Verificar si la contraseña ya está encriptada (opcional)
			if (!usuario.getPasahitza().startsWith("$2a$")) { // Patrón típico de contraseñas encriptadas con BCrypt
				// Encriptar la contraseña
				String encriptada = passwordEncoder.encode(usuario.getPasahitza());
				usuario.setPasahitza(encriptada);

				// Guardar los cambios
				erabRepo.save(usuario);
			}
		}

		return "Contraseñas encriptadas correctamente para " + usuarios.size() + " usuarios.";
	}
}
