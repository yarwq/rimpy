package com.rimp.rimpy;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

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
                              @RequestParam(value = "image", required = false) MultipartFile image,
                              Principal principal) throws IOException {
        User user = userRepository.findByLogin(principal.getName());
        Chat chat = chatRepository.findById(chatId).orElse(null);

        if (user == null || chat == null) {
            return null;
        }

        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();

            Path uploadPath = Paths.get("uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(filename);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            imageUrl = "/uploads/" + filename;
        }

        Message message = new Message();
        message.setMessage(text);
        message.setUser(user);
        message.setChat(chat);
        message.setImageUrl(imageUrl);

        return repo.save(message);
    }


}
