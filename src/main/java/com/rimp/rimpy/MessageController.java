package com.rimp.rimpy;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    MessageRepo repo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository;

    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getMessages(@RequestParam("chatId") Long chatId) {
        return repo.findByChatId(chatId);
    }
    @PostMapping("/message")
    @ResponseBody
    public Message addMessage(@RequestParam("message") String text,
                              @RequestParam("chat.id") Long chatId,
                              Principal principal) {
        User user = userRepository.findByLogin(principal.getName());
        Chat chat = chatRepository.findById(chatId).orElse(null);

        if (user == null || chat == null) {
            return null;
        }

        Message message = new Message();
        message.setMessage(text);
        message.setUser(user);
        message.setChat(chat);

        return repo.save(message);
    }


}
