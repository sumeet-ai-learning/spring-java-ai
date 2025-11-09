package com.learning.multimodel.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean(name = "openAiChatClient")
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.create(openAiChatModel);
    }

    @Primary
    @Bean(name = "ollamaChatClient")
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        ChatClient.Builder chatClientBulder = ChatClient.builder(ollamaChatModel)
                .defaultAdvisors(List.of(new TokenLoggerAdvisor(),new SimpleLoggerAdvisor()));
//                .defaultOptions(prepareOptions());
//                .defaultOptions(OllamaOptions.builder().model(OllamaModel.LLAMA3_2_3B).build());
        return chatClientBulder.build();
    }

    private ChatOptions prepareOptions() {
        return ChatOptions.builder().stopSequences(List.of("the")).build();
    }

}
