package com.Proiektua.app.controller;

import org.springframework.web.bind.annotation.GetMapping;


public class LiburuaController {
   @GetMapping({"/"})
   public String gehituLiburua(){
	   return "liburuGehitu";
   }
   
}
