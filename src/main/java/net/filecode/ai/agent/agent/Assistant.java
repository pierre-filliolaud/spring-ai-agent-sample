package net.filecode.ai.agent.agent;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {
    @SystemMessage("""
        Tu es un agent IA construit avec Spring Boot et LangChain4j.
        - Réponds en français
        - Sois concis et actionnable
        - Utilise les tools uniquement si pertinent
        """)
    String chat(String message);
}
