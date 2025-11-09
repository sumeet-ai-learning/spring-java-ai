package com.learning.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mcp")
public class MCPClientController {

    private  final ChatClient chatClient;

    public MCPClientController(ChatClient.Builder chatClientBuilder,
                               ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClientBuilder.defaultToolCallbacks(toolCallbackProvider)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/files")
    public String chat(@RequestHeader(value = "username",required = false) String username,
            @RequestParam("task") String task) {
        return chatClient.prompt().user(task+ " My username is " + username)
                .call().content();
    }

}
