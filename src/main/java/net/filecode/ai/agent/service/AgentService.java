package net.filecode.ai.agent.service;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import net.filecode.ai.agent.agent.Assistant;
import net.filecode.ai.agent.tool.BasicsTool;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final Assistant assistant;

    public AgentService(ChatModel chatModel, BasicsTool basicsTool) {
        this.assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .tools(basicsTool)
                .build();
    }

    public String ask(String msg) {
        return assistant.chat(msg);
    }
}
