package com.example.ai_assistant.modules.ai_response.services.ai_response.implementation;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.ChatMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.stereotype.Service;

import com.example.ai_assistant.modules.ai_response.models.Document;
import com.example.ai_assistant.modules.ai_response.services.ai_response.IAiChatService;
import com.example.ai_assistant.modules.ai_response.services.knowledge_base.IKnowledgeBaseService;
import com.example.ai_assistant.modules.chat_history.entities.AppChatMessage;
import com.example.ai_assistant.modules.chat_history.services.chat_history.IChatService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AiResponseService implements IAiChatService {

    private final OpenAiChatClient chatClient;
    private final IKnowledgeBaseService knowledgeBaseService;
    private final IChatService chatService;

    @Override
    public AppChatMessage createStringResponse(AppChatMessage userMessage) {
        String query = userMessage.getContent();
        String userId = userMessage.getUserId();
        chatService.createMessage(AppChatMessage.builder().content(query).isBot(false).userId(userId).build());
        var chatHistory = chatService.getMessagesByUserId(userId);
        List<Document> searchResult = knowledgeBaseService.search(query);
        ChatResponse chatResponse = chatClient.call(new Prompt(List.<Message>of(
                new ChatMessage(MessageType.SYSTEM, getSystemPrompt(searchResult, chatHistory)),

                new ChatMessage(MessageType.USER, query))));

        String aiResponse = chatResponse.getResult().getOutput().getContent();
        chatService.createMessage(AppChatMessage.builder().content(aiResponse).isBot(true).build());

        return AppChatMessage.builder().content(aiResponse).isBot(true).createdAt(new Date()).build();
    }

    private String getSystemPrompt(List<Document> searchResult,
            List<AppChatMessage> chatMessages) {
        String combinedDocuments = searchResult.stream()
                .map(Document::getContent)
                .collect(Collectors.joining("\n"));

        String combinedMessages = chatMessages.stream().map(chatMessage -> {
            return MessageFormat.format("""
                            Sent by: {1}
                            Content:
                            {0}
                    """, chatMessage.getContent(), chatMessage.getIsBot() ? "You" : "User");
        }).collect(Collectors.joining("\n"));

        return MessageFormat.format(
                """
                        You are helpful manager of car service. You role is to provide useful information to user about our service.
                        Behavior instructions:
                        1. You should be polite to user.
                        2. Don't provide information that is not related to your role as a car service manager.
                        3. Try your best to answer to user's question.
                        4. Don't provide information that is not mentioned inside of result XML tag.
                        5. If you don't have enough information to answer the question, you should explain this to user.
                            <result>{0}</result>
                        Currently you are having a conversation with user:
                        {1}
                            """,
                combinedDocuments, combinedMessages);
    }

}
