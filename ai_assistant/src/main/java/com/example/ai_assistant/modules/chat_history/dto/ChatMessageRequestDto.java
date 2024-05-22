package com.example.ai_assistant.modules.chat_history.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessageRequestDto {

    @Schema(description = "Content of the chat message", example = "Hello, how can I assist you?")
    @NotBlank(message = "Content cannot be blank")
    private String content;
}
