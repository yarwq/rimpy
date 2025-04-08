package com.rimp.rimpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/home")
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String home(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByLogin(username);
        System.out.println(user);
        String name = user.getName();
        System.out.println(name);
        model.addAttribute("name", name);
        //model.addAttribute("chats", user.getChat());
        return "home";
    }
}
