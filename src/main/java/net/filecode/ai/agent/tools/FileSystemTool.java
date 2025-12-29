package net.filecode.ai.agent.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileSystemTool {
    private final Path root;

    public FileSystemTool(@Value("${app.workspace.root:./workspace}") String rootDir) {
        this.root = Path.of(rootDir).toAbsolutePath().normalize();
    }

    @Tool("""
        Creates a Markdown file under the workspace root directory.
        Provide a relative path ending with .md (e.g., notes/todo.md) and the markdown content.
        Returns the absolute path of the created file.
        """)
    public String createMarkdownFile(String relativePath, String content) throws Exception {
        if (relativePath == null || relativePath.isBlank()) {
            throw new IllegalArgumentException("relativePath is required");
        }

        // Basic validation
        String rp = relativePath.trim().replace("\\", "/");
        if (!rp.endsWith(".md")) {
            throw new IllegalArgumentException("File must end with .md");
        }
        if (rp.startsWith("/") || rp.contains("..")) {
            throw new IllegalArgumentException("Path must be a safe relative path (no absolute path, no '..').");
        }

        Path target = root.resolve(rp).normalize();

        // Enforce sandbox: target must stay inside root
        if (!target.startsWith(root)) {
            throw new IllegalArgumentException("Target path escapes workspace root.");
        }

        Files.createDirectories(target.getParent());

        // Avoid overwriting by default (change if you want)
        if (Files.exists(target)) {
            throw new IllegalStateException("File already exists: " + target);
        }

        Files.writeString(target, content == null ? "" : content, StandardCharsets.UTF_8);

        return target.toString();
    }
}
