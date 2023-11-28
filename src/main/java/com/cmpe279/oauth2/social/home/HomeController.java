package com.cmpe279.oauth2.social.home;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String home(Model model){
        model.addAttribute("message", "Welcome to the CMPE279 OAuth 2.0 Demo !");
        return "home";
    }
}
