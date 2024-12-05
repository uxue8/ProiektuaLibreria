package com.Proiektua.app.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
	@GetMapping("/logina")
	   public String Logina(){
		   return "Logina";
	   }
}
