package com.cmpe279.oauth2.social.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello from a secured endpoint");
    }
}
