package com.rimp.rimpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class SingInController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/index")
    public String loginForm() {
        return "index"; // index.html — форма логина
    }
    @PostMapping("/user")
    public String user(@RequestParam String login, Principal principal, Model model) {
        User FINDINGUSER =userRepository.findByLogin(login);
        String nameUser = FINDINGUSER.getName();
        System.out.println(nameUser);
        model.addAttribute("name", nameUser);
        model.addAttribute("login", login);
        if (FINDINGUSER != null) {
            return "user";
           // return "/user/{name}";
        }
        else {
            return "home";
        }
    }
    @GetMapping("/user")
    public String viewUser( Model model) {
        return "user";
    }
    }
