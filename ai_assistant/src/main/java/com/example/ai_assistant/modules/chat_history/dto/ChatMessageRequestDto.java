package com.example.ai_assistant.modules.chat_history.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessageRequestDto {
    @NotBlank
    private String content;
}
