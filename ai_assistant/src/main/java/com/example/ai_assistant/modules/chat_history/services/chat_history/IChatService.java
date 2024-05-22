package com.example.ai_assistant.modules.chat_history.services.chat_history;

import java.util.List;

import com.example.ai_assistant.modules.chat_history.entities.AppChatMessage;

public interface IChatService {
    AppChatMessage createMessage(AppChatMessage chatMessage);

    List<AppChatMessage> getMessagesByUserId(String userId);
}
