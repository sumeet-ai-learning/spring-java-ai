package com.learning.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/prompt")
public class PromptTemplateContoller {

    private final ChatClient openAiChatClient;

    public PromptTemplateContoller(@Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
    }

}
