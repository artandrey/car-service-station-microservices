package com.example.ai_assistant.modules.chat_history.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_assistant.modules.ai_response.services.ai_response.IAiChatService;
import com.example.ai_assistant.modules.chat_history.dto.ChatMessageRequestDto;
import com.example.ai_assistant.modules.chat_history.dto.ChatMessageResponseDto;
import com.example.ai_assistant.modules.chat_history.entities.AppChatMessage;
import com.example.ai_assistant.modules.chat_history.mappers.ChatMessageMapper;
import com.example.ai_assistant.modules.chat_history.services.chat_history.implementation.ChatService;
import com.example.ai_assistant.modules.shared.util.JwtUtil;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/conversation")
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final ChatMessageMapper chatMessageMapper;
    private final IAiChatService aiResponseService;

    @GetMapping("/chat/messages")
    public List<ChatMessageResponseDto> getUserMessages(@PathVariable String userId,
            @RequestHeader("Authorization") String authorization) {
        String identityUserId = JwtUtil.mapJwtToUser(authorization).getSid();
        return chatService.getMessagesByUserId(identityUserId)
                .stream()
                .map(chatMessageMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/message")
    public ResponseEntity<ChatMessageResponseDto> postMessage(
            @Valid @RequestBody ChatMessageRequestDto chatMessageRequestDto,
            @RequestHeader("Authorization") String authorization) {
        String userId = JwtUtil.mapJwtToUser(authorization).getSid();
        AppChatMessage userMessage = chatMessageMapper.fromDto(chatMessageRequestDto, userId);
        AppChatMessage responseMessage = aiResponseService.createStringResponse(userMessage);
        return ResponseEntity.ok(chatMessageMapper.toDto(responseMessage));
    }
}
