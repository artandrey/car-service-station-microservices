package com.example.ai_assistant.modules.chat_history.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/conversation")
@AllArgsConstructor
public class ChatController {

        private final ChatService chatService;
        private final ChatMessageMapper chatMessageMapper;
        private final IAiChatService aiResponseService;

        @Operation(summary = "Get user chat history", description = "Retrieve chat history with AI for a specific user.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved messages"),
        })
        @GetMapping("/chat/messages")
        public List<ChatMessageResponseDto> getUserMessages(
                        @RequestHeader(value = "Authorization", required = false) String authorization) {
                String identityUserId = JwtUtil.mapJwtToUser(authorization).getSid();
                return chatService.getMessagesByOwnerUserId(identityUserId)
                                .stream()
                                .map(chatMessageMapper::toDto)
                                .collect(Collectors.toList());
        }

        @Operation(summary = "Retrieve response", description = "Post a chat message and retrieve response to the message from AI")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully posted message"),
                        @ApiResponse(responseCode = "400", description = "Bad request"),
        })
        @PostMapping("/message")
        public ResponseEntity<ChatMessageResponseDto> postMessage(
                        @Valid @RequestBody ChatMessageRequestDto chatMessageRequestDto,
                        @RequestHeader(value = "Authorization", required = false) String authorization) {
                String userId = JwtUtil.mapJwtToUser(authorization).getSid();
                AppChatMessage userMessage = chatMessageMapper.fromDto(chatMessageRequestDto, userId);
                AppChatMessage responseMessage = aiResponseService.createStringResponse(userMessage);
                return ResponseEntity.ok(chatMessageMapper.toDto(responseMessage));
        }
}
