package com.example.multiagent.worker;

import com.google.adk.agents.Agent;
import com.google.adk.agents.LlmAgent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnalyticalWorkerAgentTest {

    @Test
    void createAgent_shouldReturnNonNullAgent() {
        Agent agent = AnalyticalWorkerAgent.createAgent();
        assertNotNull(agent, "Agent should not be null.");
    }

    @Test
    void createAgent_shouldSetCorrectName() {
        Agent agent = AnalyticalWorkerAgent.createAgent();
        assertEquals("AnalyticalWorker", agent.name(), "Agent name should be 'AnalyticalWorker'.");
    }

    @Test
    void createAgent_shouldConfigureModelFromTestProperties() {
        Agent agent = AnalyticalWorkerAgent.createAgent();
        assertTrue(agent instanceof LlmAgent, "Agent should be an instance of LlmAgent.");
        LlmAgent llmAgent = (LlmAgent) agent;
        assertEquals("test-model-from-properties", llmAgent.model().name(), "Agent model name should match test config.");
    }
}
