package com.Proiektua.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Proiektua.app.modelo.Editoriala;
import com.Proiektua.app.modelo.Liburua;
import com.Proiektua.app.repository.EditorialaRepository;
import com.Proiektua.app.repository.LiburuaRepository;



@Controller
public class LiburuaController {

	@Autowired
	private LiburuaRepository libuRepo;
	
	@Autowired
	private EditorialaRepository ediRepo;
	
   @GetMapping("/liburuaGehitu")
   public String LiburuaForm(Model model){
	   List<Editoriala> editoriales = ediRepo.findAll();
	    model.addAttribute("editoriales", editoriales);
	   model.addAttribute("liburua", new Liburua());
	   return "LiburuGehitu";
   }
   
   @PostMapping("/liburuaGehitu/add")
   public String gehitu(@ModelAttribute("liburua") Liburua liburua,@RequestParam("irudia") MultipartFile irudia) {
	   
	   
	   try {
           if (!irudia.isEmpty()) {
               Path directorioImagen= Paths.get("src//main//resources//static//images");
               String rutaAbsoluta=directorioImagen.toFile().getAbsolutePath();
               byte[] bytesImg= irudia.getBytes();
               Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+irudia.getOriginalFilename());
               Files.write(rutaCompleta,bytesImg);
               liburua.setIrudia(irudia.getOriginalFilename());
               libuRepo.save(liburua);
           }

          

           return "redirect:/liburuaGehitu"; // O cualquier vista que prefieras
       } catch (IOException e) {
           e.printStackTrace();
           return "error";  // Si ocurre un error, mostramos una página de error
       }
  
   
   }
}

   

