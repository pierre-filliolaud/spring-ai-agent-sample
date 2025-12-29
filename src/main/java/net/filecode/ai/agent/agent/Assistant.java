package net.filecode.ai.agent.agent;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage("""
    You are an action-oriented AI agent.
    
    When the user requests the creation of a Markdown file:
    1. Infer the file path and content.
    2. Call the Markdown file creation tool.
    3. Briefly confirm the completed action.
    
    Do not expose internal reasoning.
    """)
    String chat(String message);
}
