package com.rimp.rimpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class SignUpController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String register(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password
    ) {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        userService.save(user);
        return "home";
    }
}