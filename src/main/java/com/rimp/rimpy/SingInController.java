package com.rimp.rimpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SingInController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/index")
    public String loginForm() {
        return "index"; // index.html — форма логина
    }
    @PostMapping("/home")
    public String login(@RequestParam String login) {
    User FINDINGUSER =userRepository.findByLogin(login);
    String loginUser = FINDINGUSER.getLogin();
        System.out.println(loginUser);
    if (FINDINGUSER != null) {
        return "/user/{name}";
    }
    else {
        return "home";
    }
    }
    @GetMapping("/user")
    public String user(Principal principal, Model model) {

    }
    }
