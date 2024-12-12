package com.Proiektua.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Proiektua.app.modelo.Cesta;
import com.Proiektua.app.modelo.Editoriala;
import com.Proiektua.app.modelo.Liburua;
import com.Proiektua.app.repository.CestaRepository;
import com.Proiektua.app.repository.EditorialaRepository;
import com.Proiektua.app.repository.LiburuaRepository;



@Controller
public class LiburuaController {

	@Autowired
	private LiburuaRepository libuRepo;
	
	@Autowired
	private EditorialaRepository ediRepo;
	
	@Autowired
	private CestaRepository cesRepo;
	
	private ArrayList<Liburua> liburu_erosita = new ArrayList<>();
	
	
	@GetMapping("/liburuak")
   public String liburuIkusi(Model model) {
		 List<Liburua> erabiltzaileakList = libuRepo.findAll();
	        model.addAttribute("liburuak", erabiltzaileakList);
		
		return "liburuak";
	   
   }
	
   @GetMapping("/liburuaGehitu")
   public String LiburuaForm(Model model){
	   List<Editoriala> editoriales = ediRepo.findAll();
	    model.addAttribute("editoriales", editoriales);
	   model.addAttribute("liburua", new Liburua());
	   return "LiburuGehitu";
   }
   
   @PostMapping("/liburuaGehitu/add")
   public String gehitu(@ModelAttribute("liburua") Liburua liburua,@RequestParam("file") MultipartFile irudia) {
	   
	   
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
	        return "redirect:/liburuaGehitu"; // Redireccionar después de guardar
       } catch (IOException e) {
           e.printStackTrace();
           return "error";  // Si ocurre un error, mostramos una página de error
       }
  
   
   }
   
   @PostMapping("/comprar")
   public String comprar(@ModelAttribute("liburua")Liburua liburua,@RequestParam Long id, @RequestParam int cantidad) {
       // Lógica para procesar la compra con el ID y la cantidad del libro
	    
    
       
       Optional<Liburua> libu= libuRepo.findById(liburua.getId());
    
       System.out.println("Libro ID: " + id + ", Cantidad: " + cantidad+libu.get());
       
       Cesta ce = new Cesta();
          if(cantidad>1) {
        	  for(int i=0; i<cantidad; i++) {
        		  
        		   liburu_erosita.add(libu.get());
        		   
        		  
        		   
        		   
        	  }
        	  ce.setLiburu_erosita(liburu_erosita);
        	  cesRepo.save(ce);
        	         	  
          }else {
        	  
        	   liburu_erosita.add(libu.get());
        	   ce.setLiburu_erosita(liburu_erosita);
        	   cesRepo.save(ce);
          }
   
      
       
       return "redirect:/liburuak";
   }
   
   
}

   

