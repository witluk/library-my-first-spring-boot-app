package com.witluk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping(value={"/","/about"})
    public String about(){
        return "about";
    }
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@GetMapping("/librarian")
    public String admin() {
        return "librarian";
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
