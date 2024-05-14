package com.example.ai_assistant.modules.chat_history.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_assistant.modules.chat_history.dto.ChatMessageResponseDto;
import com.example.ai_assistant.modules.chat_history.mappers.ChatMessageMapper;
import com.example.ai_assistant.modules.chat_history.services.chat_history.implementation.ChatService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/conversation")
@AllArgsConstructor
public class ChatHistoryController {
    private final ChatService chatService;
    private final ChatMessageMapper chatMessageMapper;

    @GetMapping("/user/{userId}/messages")
    public List<ChatMessageResponseDto> getUserMessages(@PathVariable String userId) {
        return chatService.getMessagesByUserId(userId)
                .stream()
                .map(chatMessageMapper::toDto)
                .collect(Collectors.toList());
    }
}
