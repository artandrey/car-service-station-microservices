package com.example.ai_assistant.modules.chat_history.services.chat_history;

import java.util.List;

import com.example.ai_assistant.modules.chat_history.entities.ChatMessage;

public interface IChatService {
    ChatMessage createMessage(ChatMessage chatMessage);

    List<ChatMessage> getMessagesByUserId(String userId);
}
