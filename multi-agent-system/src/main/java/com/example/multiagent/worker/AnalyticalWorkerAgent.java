package com.example.multiagent.worker;

import com.google.adk.agents.LlmAgent;
import com.google.adk.agents.Agent;
import com.example.multiagent.config.ConfigLoader; // Added import

public class AnalyticalWorkerAgent {

    public static Agent createAgent() {
        String modelName = ConfigLoader.getModelName(); // Load model name
        // String apiKey = ConfigLoader.getApiKey();

        // Note on custom model configuration is same as for CreativeWorkerAgent

        return LlmAgent.builder()
            .name("AnalyticalWorker")
            .description("A worker agent that specializes in analytical tasks, logical reasoning, calculations, and data interpretation.")
            .model(modelName) // Use loaded model name
            .instruction(
                "You are an analytical assistant. Your primary function is to help with tasks that require logic, reasoning, and data analysis. " +
                "This includes solving math problems, explaining complex concepts step-by-step, " +
                "interpreting data, performing calculations, and providing factual answers based on provided information. " +
                "Focus on accuracy, logical consistency, and clear explanations. " +
                "If asked to do something highly creative or artistic without an analytical component, you may politely state your specialization."
            )
            .build();
    }
}
