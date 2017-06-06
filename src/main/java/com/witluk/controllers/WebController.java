package com.witluk.controllers;

import com.witluk.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.witluk.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

	@RequestMapping(value={"/","/about"})
    public String about(){
        return "about";
    }
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@GetMapping("/librarian")
    public String admin(Model model) {
	    model.addAttribute("users", userRepository.findAll());
        return "librarian";
    }

    @RequestMapping(value="/librarian/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        userRepository.delete(id);
        return "redirect:/librarian";
    }

    @GetMapping("/reader")
    public String user() {
        return "reader";
    }
    
    @GetMapping("/403")
    public String error403() {
        return "403";
    }
	
}
