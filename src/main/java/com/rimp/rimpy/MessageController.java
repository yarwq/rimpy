package com.rimp.rimpy;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MessageController {
    @Autowired
    MessageRepo repo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository;
    @PostMapping("/message")
    public String addMessage(@RequestParam("message") String text,
                             @RequestParam("chat.id") Long chatId,
                             Principal principal) {

        User user = userRepository.findByLogin(principal.getName());
        Chat chat = chatRepository.findById(chatId).orElse(null);

        if (user == null || chat == null) {
            return "redirect:/home";
        }

        Message message = new Message();
        message.setMessage(text);
        message.setUser(user);
        message.setChat(chat);

        repo.save(message);

        return "redirect:/chats/" + chatId;
    }

}
