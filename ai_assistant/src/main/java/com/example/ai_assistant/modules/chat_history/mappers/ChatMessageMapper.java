package com.example.ai_assistant.modules.chat_history.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.ai_assistant.modules.chat_history.dto.ChatMessageResponseDto;
import com.example.ai_assistant.modules.chat_history.entities.ChatMessage;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ChatMessageMapper {

    private final ModelMapper modelMapper;

    public ChatMessageResponseDto toDto(ChatMessage chatMessage) {
        return modelMapper.map(chatMessage, ChatMessageResponseDto.class);
    }
}