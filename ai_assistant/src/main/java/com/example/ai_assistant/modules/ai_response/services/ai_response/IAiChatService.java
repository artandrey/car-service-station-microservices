package com.example.ai_assistant.modules.ai_response.services.ai_response;

import com.example.ai_assistant.modules.chat_history.entities.AppChatMessage;

public interface IAiChatService {
    AppChatMessage createStringResponse(AppChatMessage userMessage);
}
