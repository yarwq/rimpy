package com.rimp.rimpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.UUID;

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
        model.addAttribute("avatar", user.getAvatar());
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
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                user.getLogin(), user.getPassword(), SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "redirect:/profile";
    }
    @PostMapping("/updateAvatar")
    public String changeAvatar(@RequestParam("file") MultipartFile file, Principal principal) throws IOException, IOException {
        if (file.isEmpty()) return "redirect:/profile";

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get("src/main/resources/static/uploads");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        User user = userRepository.findByLogin(principal.getName());

        if (user == null) return "redirect:/login?error";

        user.setAvatar("/uploads/" + fileName);
        userRepository.save(user);

        return "redirect:/profile";
    }

}
