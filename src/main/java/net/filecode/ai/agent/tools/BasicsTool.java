package net.filecode.ai.agent.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class BasicsTool {
    @Tool("Retourne la date et l'heure actuelles (ISO-8601)")
    public String now() {
        return ZonedDateTime.now().toString();
    }

    @Tool("Additionne deux nombres entiers")
    public int add(int a, int b) {
        return a + b;
    }
}
