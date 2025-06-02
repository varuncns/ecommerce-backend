package com.ecommerce.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername(); // email from JWT
        return "Welcome " + username + " to OneCartforEverything!";
    }
}
