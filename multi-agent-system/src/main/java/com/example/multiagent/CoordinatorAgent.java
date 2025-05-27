package com.example.multiagent;

import com.google.adk.agents.Agent;
import com.google.adk.agents.LlmAgent;
import com.example.multiagent.worker.CreativeWorkerAgent;
import com.example.multiagent.worker.AnalyticalWorkerAgent;
import com.example.multiagent.config.ConfigLoader; // Added import

public class CoordinatorAgent {

    public static Agent ROOT_AGENT = initializeCoordinator();

    private static Agent initializeCoordinator() {
        String modelName = ConfigLoader.getModelName(); // Load model name
        // String apiKey = ConfigLoader.getApiKey(); // API key for client if needed

        // Note on custom model configuration is same as for WorkerAgents.
        // The Coordinator might use a different model specified in config,
        // or the same one. Here, we assume it uses the same llm.model_name for simplicity.
        // If it needed a *different* model than workers, config file would need another property e.g., llm.coordinator_model_name

        Agent creativeWorker = CreativeWorkerAgent.createAgent();
        Agent analyticalWorker = AnalyticalWorkerAgent.createAgent();

        return LlmAgent.builder()
            .name("MasterCoordinatorAgent")
            .description("A coordinator agent that understands user requests and delegates them to specialized worker agents.")
            .model(modelName) // Use loaded model name
            .instruction(
                "You are a master coordinator for a team of specialized AI agents. " +
                "Your primary role is to understand incoming user requests and delegate them to the most appropriate worker agent. " +
                "You have the following workers available: " +
                "1. CreativeWorker: Specializes in creative tasks like writing, brainstorming, and artistic text generation. " +
                "2. AnalyticalWorker: Specializes in analytical tasks, logical reasoning, calculations, and data interpretation. " +
                "Based on the user's request, determine which worker is best suited. " +
                "Use the 'transfer_to_agent' function to delegate the task to the chosen worker's name (e.g., 'CreativeWorker' or 'AnalyticalWorker'). " +
                "Provide the full user request to the worker. " +
                "If the request is ambiguous, you can ask clarifying questions. " +
                "If the request seems to require both, you might try to break it down or choose the primary aspect. " +
                "Your sub-agents are: CreativeWorker, AnalyticalWorker."
            )
            .subAgents(creativeWorker, analyticalWorker)
            .build();
    }
}
