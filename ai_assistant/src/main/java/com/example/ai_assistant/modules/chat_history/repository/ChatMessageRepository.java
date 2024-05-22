package com.example.ai_assistant.modules.chat_history.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ai_assistant.modules.chat_history.entities.AppChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<AppChatMessage, Long> {
    List<AppChatMessage> findByUserId(String userId);
}
