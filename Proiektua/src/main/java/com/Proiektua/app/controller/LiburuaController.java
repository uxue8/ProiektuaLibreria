package com.Proiektua.app.controller;

import org.springframework.web.bind.annotation.GetMapping;


public class LiburuaController {
   @GetMapping({"/liburua/save"})
   public String gehituLiburua(){
	   return "liburuGehitu";
   }
   
}
