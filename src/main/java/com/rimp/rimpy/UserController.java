package com.rimp.rimpy;

import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/home")
@Controller
public class UserController {
    @GetMapping
   public String home(Model model, HttpExchange.Principal principal) {
        String username = principal.getName();
        model.addAttribute("name", username);
        return "home";
    }
}
