package com.example.multiagent.worker;

import com.google.adk.agents.LlmAgent;
import com.google.adk.agents.Agent;
import com.example.multiagent.config.ConfigLoader; // Added import

public class CreativeWorkerAgent {

    public static Agent createAgent() {
        String modelName = ConfigLoader.getModelName(); // Load model name
        // String apiKey = ConfigLoader.getApiKey(); // API key can be loaded if needed for direct Client construction

        // Note: If modelName is for a standard Gemini model, and GOOGLE_API_KEY is set, ADK might use it.
        // If modelName is custom (like "hosted_vllm/DeepSeek-R1-108"), ADK will pass this name.
        // Applying custom api_base or ssl_verify from ConfigLoader to ADK's HTTP client
        // for arbitrary models is not directly supported by LlmAgent.builder() based on current docs.

        return LlmAgent.builder()
            .name("CreativeWorker")
            .description("A worker agent that excels at creative tasks like writing stories, brainstorming ideas, or generating artistic text.")
            .model(modelName) // Use loaded model name
            .instruction(
                "You are a creative assistant. Your primary function is to help with tasks that require imagination and originality. " +
                "This includes writing stories, poems, or scripts, brainstorming innovative ideas for projects, " +
                "generating marketing copy, or describing things in an artistic and engaging manner. " +
                "Focus on novelty, style, and imaginative solutions. " +
                "If asked to do something highly analytical or data-driven without a creative angle, you may politely state your specialization."
            )
            .build();
    }
}
