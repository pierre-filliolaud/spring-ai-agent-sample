package net.filecode.ai.agent.service;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import net.filecode.ai.agent.agent.Assistant;
import net.filecode.ai.agent.tools.BasicsTool;
import net.filecode.ai.agent.tools.FileSystemTool;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final Assistant assistant;

    public AgentService(ChatModel chatModel, BasicsTool basicsTool, FileSystemTool fileSystemTool) {
        this.assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .tools(basicsTool, fileSystemTool)
                .build();
    }

    public String ask(String msg) {
        return assistant.chat(msg);
    }
}
