package com.rimp.rimpy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    public List<Message> findByChat(Chat chat);
}
