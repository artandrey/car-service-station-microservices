package com.example.ai_assistant.modules.chat_history.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageResponseDto {

    @Schema(description = "Content of the chat message", example = "Hello, how can I assist you?")
    private String content;

    @Schema(description = "User ID associated with the message")
    private String userId;

    @Schema(description = "Flag indicating if the message was sent by a bot")
    private Boolean isBot;

    @Schema(description = "Timestamp when the message was created")
    private Date createdAt;
}
