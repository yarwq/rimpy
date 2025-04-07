package com.rimp.rimpy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register.html")
public class SingUpController {
    UserService userService;
    @PostMapping
    public String register(@RequestParam User user) {
        userService.save(user);
            return "home";
    }
}
