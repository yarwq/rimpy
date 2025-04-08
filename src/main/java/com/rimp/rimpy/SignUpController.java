package com.rimp.rimpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/register")
public class SignUpController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String login,
                           @RequestParam String password,
                           Model model) {
        if (userRepository.findByLogin(login) != null) {
            model.addAttribute("error", "Пользователь уже существует");
            return "register";
        }
        User user = new User(name, login, passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/index";
    }
}