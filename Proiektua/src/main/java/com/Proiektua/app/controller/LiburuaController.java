package com.Proiektua.app.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Proiektua.app.modelo.Liburua;
import com.Proiektua.app.repository.LiburuaRepository;



@Controller
public class LiburuaController {
	private static final String UPLOAD_DIR = null;
	@Autowired
	private LiburuaRepository libuRepo;
   @GetMapping("/liburuaGehitu")
   public String LiburuaForm(Model model){
	   model.addAttribute("liburua", new Liburua());
	   return "LiburuGehitu";
   }
   
   @PostMapping("/liburuaGehitu/add")
   public String gehitu(@ModelAttribute Liburua liburua,@RequestParam("irudia") MultipartFile imagen) {
	   
	   System.out.println("Formulario recibido!");
	   if (!imagen.isEmpty()) {
           try {
               // Crear el archivo en el directorio de carga
               String fileName = imagen.getOriginalFilename();
               Path filePath = Paths.get(UPLOAD_DIR, fileName);
               imagen.transferTo(filePath.toFile());
               
               // Guardar la ruta del archivo en la entidad
               liburua.setIrudia(filePath.toString());
           } catch (IOException e) {
               e.printStackTrace();
               return "error"; // Si ocurre un error al subir la imagen
           }
       }

	   libuRepo.save(liburua);
       return "redirect:/liburuaGehitu";
   }
   
}
