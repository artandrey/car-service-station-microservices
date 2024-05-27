package com.example.ai_assistant;

import com.example.ai_assistant.modules.ai_response.models.Document;
import com.example.ai_assistant.modules.ai_response.services.ai_response.implementation.AiResponseService;
import com.example.ai_assistant.modules.ai_response.services.knowledge_base.IKnowledgeBaseService;
import com.example.ai_assistant.modules.chat_history.entities.AppChatMessage;
import com.example.ai_assistant.modules.chat_history.services.chat_history.IChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AiResponseServiceTest {

        @Mock
        private OpenAiChatClient chatClient;

        @Mock
        private IKnowledgeBaseService knowledgeBaseService;

        @Mock
        private IChatService chatService;

        @InjectMocks
        private AiResponseService aiResponseService;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testCreateStringResponse() {
                String query = "What is the status of my car repair?";
                String userId = "user123";
                AppChatMessage userMessage = AppChatMessage.builder()
                                .content(query)
                                .chatOwnerId(userId)
                                .isBot(false)
                                .build();

                List<AppChatMessage> chatHistory = List.of(
                                AppChatMessage.builder().content("Previous message").isBot(false).build());
                List<Document> searchResult = List.of(
                                new Document("Document content"));

                when(chatService.getMessagesByOwnerUserId(userId)).thenReturn(chatHistory);
                when(knowledgeBaseService.search(query)).thenReturn(searchResult);

                ChatResponse chatResponse = mock(ChatResponse.class);
                Generation generation = mock(Generation.class);
                AssistantMessage output = mock(AssistantMessage.class);

                when(chatResponse.getResult()).thenReturn(generation);
                when(generation.getOutput()).thenReturn(output);
                when(output.getContent()).thenReturn("AI response");

                when(chatClient.call(any(Prompt.class))).thenReturn(chatResponse);

                AppChatMessage expectedResponse = AppChatMessage.builder()
                                .content("AI response")
                                .isBot(true)
                                .createdAt(any(Date.class))
                                .build();

                AppChatMessage actualResponse = aiResponseService.createStringResponse(userMessage);

                assertEquals(expectedResponse.getContent(), actualResponse.getContent());
                assertEquals(expectedResponse.getIsBot(), actualResponse.getIsBot());

                verify(chatService, times(1)).createMessage(userMessage);
        }

}
