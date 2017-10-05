package br.com.beani.currencyConverter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.beani.currencyConverter.Login;

@Controller
public class HomeController
{

   @GetMapping("/")
   public String index(Model model)
   {
	  model.addAttribute("login", new Login());
      return "/home/index";
   }

   @PostMapping("/")
   public String loginSubmit(@ModelAttribute Login login) {
		if(login.validateLogin()){
			return "user/list";
		}
		else 
			return "home/index";
   }
}
