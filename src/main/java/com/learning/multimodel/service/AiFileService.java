package com.learning.multimodel.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiFileService {

    private final ChatClient chatClient;

    public AiFileService(ChatClient.Builder chatClientBuilder) {
        // The builder will auto-configure the ChatClient
        // with the MCP tools defined in your properties.
        this.chatClient = chatClientBuilder.build();
    }

    public String askAboutFile(String prompt) {
        // Example: "Please summarize the file 'docs/overview.txt'"
        // The ChatClient will automatically call the MCP filesystem server
        // to get the file contents before answering.
        return this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}