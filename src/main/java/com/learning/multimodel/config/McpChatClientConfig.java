package com.learning.multimodel.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class McpChatClientConfig {

    @Bean("mcpChatClient")
    public ChatClient chatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel)
                //.defaultToolCallbacks(toolCallbackProvider)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor())).build();
    }
}

