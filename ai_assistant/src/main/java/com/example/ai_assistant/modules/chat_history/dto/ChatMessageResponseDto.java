package com.example.ai_assistant.modules.chat_history.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageResponseDto {
    private String content;
    private String userId;
    private boolean isBot;
    private Date createdAt;
}