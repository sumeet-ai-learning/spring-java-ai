package com.learning.multimodel.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatToolsConfig {

    @Bean("chatToolsClient")
    public ChatClient chatClient(OpenAiChatModel chatModel, ChatMemory chatMemory, LocalTimeTool timeTools) {
        Advisor messageAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(chatModel)
                .defaultTools(timeTools)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),
                        messageAdvisor)).build();
    }
}