package com.learning.multimodel.config;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.model.ChatResponse;

public class TokenLoggerAdvisor implements CallAdvisor {


    @Override
    public String getName() {
        return "Token advisor logger";
    }

    @Override
    public int getOrder() {
        return 1;
    }


    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = chatClientResponse.chatResponse();
        if (chatResponse.getMetadata() != null) {
            var usage = chatResponse.getMetadata().getUsage();
            if (usage != null) {
                System.out.println(usage.getTotalTokens());
            }
        }
        return chatClientResponse;
    }
}
