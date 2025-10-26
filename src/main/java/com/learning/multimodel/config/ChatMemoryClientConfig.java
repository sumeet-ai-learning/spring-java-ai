package com.learning.multimodel.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatMemoryClientConfig {

    @Bean("chatMemoryChatClient")
    public ChatClient chatClient(OllamaChatModel chatModel, ChatMemory chatMemory) {
        Advisor messageAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(chatModel)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),
                        messageAdvisor)).build();
    }
}
