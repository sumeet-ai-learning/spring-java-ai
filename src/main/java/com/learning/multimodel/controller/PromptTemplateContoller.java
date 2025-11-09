package com.learning.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController("/prompt")
public class PromptTemplateContoller {

    private final ChatClient openAiChatClient;

    public PromptTemplateContoller(@Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
    }


}
