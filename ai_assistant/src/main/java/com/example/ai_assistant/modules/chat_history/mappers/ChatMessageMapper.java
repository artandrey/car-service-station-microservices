package com.example.ai_assistant.modules.chat_history.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.ai_assistant.modules.chat_history.dto.ChatMessageRequestDto;
import com.example.ai_assistant.modules.chat_history.dto.ChatMessageResponseDto;
import com.example.ai_assistant.modules.chat_history.entities.AppChatMessage;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ChatMessageMapper {

    private final ModelMapper modelMapper;

    public ChatMessageResponseDto toDto(AppChatMessage chatMessage) {
        return modelMapper.map(chatMessage, ChatMessageResponseDto.class);
    }

    public AppChatMessage fromDto(ChatMessageRequestDto chatMessageRequestDto, String userId) {
        AppChatMessage chatMessage = modelMapper.map(chatMessageRequestDto, AppChatMessage.class);
        chatMessage.setIsBot(false);
        chatMessage.setUserId(userId);
        return chatMessage;
    }

}