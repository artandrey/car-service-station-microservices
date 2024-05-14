package com.example.ai_assistant.modules.chat_history.services.chat_history.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ai_assistant.modules.chat_history.entities.ChatMessage;
import com.example.ai_assistant.modules.chat_history.repository.ChatMessageRepository;
import com.example.ai_assistant.modules.chat_history.services.chat_history.IChatService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatService implements IChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage createMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getMessagesByUserId(String userId) {
        return chatMessageRepository.findByUserId(userId);
    }
}
