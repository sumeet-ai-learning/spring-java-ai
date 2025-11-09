package com.learning.multimodel.controller;

import com.learning.multimodel.models.CountryCodes;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api")
public class MultiModelChatController {

    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;
    private final ChatClient chatMemoryChatClient;
    private final ChatClient chatToolsClient;
    private final ChatClient mcpChatClient;

    public MultiModelChatController(@Qualifier("openAiChatClient") ChatClient openAiChatClient,
              @Qualifier("ollamaChatClient") ChatClient ollamaChatClient,
                                    @Qualifier("chatMemoryChatClient") ChatClient chatMemoryChatClient,
                                    @Qualifier("mcpChatClient") ChatClient mcpChatClient,
                                    @Qualifier("chatToolsClient") ChatClient chatToolsClient) {
        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;
        this.chatMemoryChatClient = chatMemoryChatClient;
        this.chatToolsClient = chatToolsClient;
        this.mcpChatClient = mcpChatClient;
    }

    @GetMapping("/openai/chat")
    public String openAIChat(@RequestParam("message") String message) {
        return openAiChatClient.prompt(message).call().content();
    }

    @GetMapping("/ollama/chat")
    public String ollamaChat(@RequestParam("message") String message) {
        return ollamaChatClient.prompt(message).call().content();
    }

    @Value("classpath:/promptTemplates/emailTemplate.st")
    Resource userTemplate;

    @GetMapping("/openai/email")
    public String emailResponseOpenai(@RequestParam String customerName, @RequestParam String customerMessage) {
        return openAiChatClient.prompt()
                .user(userTemplatePrompt ->
                        userTemplatePrompt.text(userTemplate)
                                .param("customerName",customerName)
                                .param("customerMessage",customerMessage))
                .system("""
                        You are a customer executive representative who is very polite and very response and \
                        always try to response the query to the best of the interest of the customer
                        """)
                .call()
                .content();
    }

    @GetMapping("/ollama/email")
    public String emailResponseOllama(@RequestParam String customerName, @RequestParam String customerMessage) {
        return ollamaChatClient.prompt()
                .user(userTemplatePrompt ->
                        userTemplatePrompt.text(userTemplate)
                                .param("customerName",customerName)
                                .param("customerMessage",customerMessage))
                .system("""
                        You are a customer executive representative who is very polite and very response and \
                        always try to response the query to the best of the interest of the customer. Draft an email \
                        to customer as all your queries will come to them.
                        """)
                .call()
                .content();
    }

    @GetMapping("/ollama/stream/email")
    public Flux<String> emailResponseOllamaStream(@RequestParam String customerName, @RequestParam String customerMessage) {
        return ollamaChatClient.prompt()
                .user(userTemplatePrompt ->
                        userTemplatePrompt.text(userTemplate)
                                .param("customerName",customerName)
                                .param("customerMessage",customerMessage))
                .system("""
                        You are a customer executive representative who is very polite and very response and \
                        always try to response the query to the best of the interest of the customer. Draft an email \
                        to customer as all your queries will come to them.
                        """)
                .stream()
                .content();
    }

    @GetMapping("/ollama/entity")
    public ResponseEntity<CountryCodes> emailResponseOllamaEntity(@RequestParam String message) {
        CountryCodes responseEntity = ollamaChatClient.prompt()
                .user(message)
                .call()
                .entity(CountryCodes.class);
        return ResponseEntity.ok(responseEntity);
    }

    @GetMapping("/memory/answer")
    public String chatMemoryQuery(@RequestParam String message, @RequestParam String uuid) {
        return chatMemoryChatClient.prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID,uuid))
                .call()
                .content();
    }

    @GetMapping("/localtime")
    public String localTime(@RequestParam String message) {
        return chatToolsClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
