package com.rimp.rimpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RequestMapping("profile")
@Controller
public class ProfileController {
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public String profile(Model model, Principal principal) {
        String nameCurrentUser = principal.getName();
        User user = userRepository.findByLogin(nameCurrentUser);
        model.addAttribute("name", user.getName());
        model.addAttribute("bio", user.getBio());
        model.addAttribute("login", user.getLogin());
        return "profile";
    }
    @PostMapping("/updateName")
    public String changeNickname(@RequestParam String name, Principal principal) {
        String nameCurrentUser = principal.getName();
        User user = userRepository.findByLogin(nameCurrentUser);
        user.setName(name);
        userRepository.save(user);
        return "redirect:/profile";
    }

    @PostMapping("/updateBio")
    public String changeBio(@RequestParam String bio, Principal principal) {
        String nameCurrentUser = principal.getName();
        User user = userRepository.findByLogin(nameCurrentUser);
        user.setBio(bio);
        userRepository.save(user);
        return "redirect:/profile";
    }

    @PostMapping("/updateLogin")
    public String changeLogin(@RequestParam String login, Principal principal) {
        String nameCurrentUser = principal.getName();
        User user = userRepository.findByLogin(nameCurrentUser);
        user.setLogin(login);
        userRepository.save(user);
        return "redirect:/profile";
    }
}
