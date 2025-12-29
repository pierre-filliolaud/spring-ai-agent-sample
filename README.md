# spring-ai-agent-sample

A minimal and extensible **AI Agent** built with **Spring Boot 4** and **LangChain4j 1.10** using the official **OpenAI Spring Boot starter**.

This project demonstrates how to build a tool-enabled agent (function calling) in Java, with a clean architecture ready for memory, RAG, and multi-agent workflows.

---

## ✨ Features

- Spring Boot 3.3.5 + Java 21
- LangChain4j 1.10 (official Spring Boot starter)
- Tool-enabled AI Agent (`@Tool`)
- Clean, extensible project structure
- REST API interface
- Production-friendly configuration

---

## 🧱 Tech Stack

- **Java 21**
- **Spring Boot 3**
- **LangChain4j 1.10**
- **OpenAI Chat Models**
- **Gradle**

---

## 📦 Dependencies (key ones)

- `spring-boot-starter-web`
- `langchain4j-open-ai-spring-boot-starter`
- `langchain4j-agent`

---

## ⚙️ Configuration

Set your OpenAI API key as an environment variable:

```bash
export OPENAI_API_KEY="your-openai-api-key"
```

Optional environment variables:

| Variable | Default | Description |
|--------|---------|-------------|
| `OPENAI_MODEL` | `gpt-4o-mini` | OpenAI chat model |
| `OPENAI_TEMPERATURE` | `0.2` | Model temperature |

`application.yml`:

```yaml
langchain4j:
  open-ai:
    chat-model:
      api-key: ${OPENAI_API_KEY}
      model-name: ${OPENAI_MODEL:gpt-4o-mini}
      temperature: 0.2
```

---

## ▶️ Run the Application

```bash
./gradlew bootRun
```

The application will start on:

```
http://localhost:8080
```

---

## 🔌 API Usage

### Chat with the Agent

**Endpoint**

```
POST /agent/chat
```

**Example**

```bash
curl -X POST http://localhost:8080/agent/chat \
  -H "Content-Type: text/plain" \
  -d "What time is it? And what is 12 + 30?"
```

---

## 🧠 Agent Design

The agent is built using `AiServices` and supports **automatic tool calling**.

Example tool:

```java
@Tool("Returns the current date and time")
public String now() {
    return ZonedDateTime.now().toString();
}
```

The LLM decides autonomously when to invoke a tool.

---

## 📁 Project Structure

```text
src/main/java/com/example/ai/agent
├── api        # REST controllers
├── agent      # Agent interfaces (AiServices)
├── service    # Agent orchestration
├── tools      # Tool implementations (@Tool)
```

---

## 🚀 Next Steps

This sample is intentionally minimal. Possible next evolutions:

- Conversation memory (per session or user)
- RAG (document ingestion + vector store)
- Multi-agent setups (planner / executor / reviewer)
- Filesystem, Git, or HTTP tools
- Autonomous agent loops (plan → act → observe)

---

## 🧩 Philosophy

This project favors:
- Explicit dependencies over magic
- Clean separation of concerns
- Agent-first design (not just chat)
- Production-oriented structure

---

## 📜 License

MIT
