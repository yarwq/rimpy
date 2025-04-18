package com.rimp.rimpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/chats/{id}")
    public String openChat(@PathVariable("id") Long chatId, Model model, Principal principal) {
        System.out.println("Открытие чата с id: " + chatId);
        Chat chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) return "redirect:/home";

        User currentUser = userRepository.findByLogin(principal.getName());
        if (!chat.getUserList().contains(currentUser)) return "redirect:/home";

        model.addAttribute("chat", chat);
        model.addAttribute("messages", messageRepo.findByChat(chat));
        return "chat";
    }

    @PostMapping("/chat")
    public String addChat(@RequestParam("login") String otherLogin, Principal principal) {
        String currentLogin = principal.getName();

        User currentUser = userRepository.findByLogin(currentLogin);
        User otherUser = userRepository.findByLogin(otherLogin);

        if (currentUser == null || otherUser == null) {
            System.out.println("один из пользователей не найден");
            return "redirect:/register";
        }
        Chat chat = new Chat();
        chat.setName(currentUser.getName() + " & " + otherUser.getName());
        List<User> users = new ArrayList<>();
        users.add(currentUser);
        users.add(otherUser);
        chat.setUserList(users);
        chatRepository.save(chat);
        return "redirect:/chats/" + chat.getId();
    }
}
