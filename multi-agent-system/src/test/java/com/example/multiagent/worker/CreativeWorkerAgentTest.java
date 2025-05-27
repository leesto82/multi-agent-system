package com.example.multiagent.worker;

import com.google.adk.agents.Agent;
import com.google.adk.agents.LlmAgent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreativeWorkerAgentTest {

    @Test
    void createAgent_shouldReturnNonNullAgent() {
        Agent agent = CreativeWorkerAgent.createAgent();
        assertNotNull(agent, "Agent should not be null.");
    }

    @Test
    void createAgent_shouldSetCorrectName() {
        Agent agent = CreativeWorkerAgent.createAgent();
        assertEquals("CreativeWorker", agent.name(), "Agent name should be 'CreativeWorker'.");
    }

    @Test
    void createAgent_shouldConfigureModelFromTestProperties() {
        Agent agent = CreativeWorkerAgent.createAgent();
        assertTrue(agent instanceof LlmAgent, "Agent should be an instance of LlmAgent.");
        LlmAgent llmAgent = (LlmAgent) agent;
        // The model() method in LlmAgent returns a ModelConfig object, 
        // and its name property holds the model name string.
        assertEquals("test-model-from-properties", llmAgent.model().name(), "Agent model name should match test config.");
    }
}
