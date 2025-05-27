package com.example.multiagent;

import com.google.adk.agents.Agent;
import com.google.adk.agents.LlmAgent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Collectors;
import java.util.List;

class CoordinatorAgentTest {

    @Test
    void rootAgent_shouldBeInitialized() {
        Agent agent = CoordinatorAgent.ROOT_AGENT;
        assertNotNull(agent, "ROOT_AGENT should not be null.");
    }

    @Test
    void rootAgent_shouldHaveCorrectName() {
        Agent agent = CoordinatorAgent.ROOT_AGENT;
        assertEquals("MasterCoordinatorAgent", agent.name(), "Agent name should be 'MasterCoordinatorAgent'.");
    }

    @Test
    void rootAgent_shouldConfigureModelFromTestProperties() {
        Agent agent = CoordinatorAgent.ROOT_AGENT;
        assertTrue(agent instanceof LlmAgent, "Agent should be an instance of LlmAgent.");
        LlmAgent llmAgent = (LlmAgent) agent;
        assertEquals("test-model-from-properties", llmAgent.model().name(), "Agent model name should match test config.");
    }

    @Test
    void rootAgent_shouldHaveSubAgents() {
        Agent agent = CoordinatorAgent.ROOT_AGENT;
        assertNotNull(agent.subAgents(), "Sub-agents list should not be null.");
        assertFalse(agent.subAgents().isEmpty(), "Sub-agents list should not be empty.");
    }

    @Test
    void rootAgent_shouldHaveCorrectSubAgentNames() {
        Agent agent = CoordinatorAgent.ROOT_AGENT;
        List<String> subAgentNames = agent.subAgents().stream()
                                          .map(Agent::name)
                                          .collect(Collectors.toList());
        assertTrue(subAgentNames.contains("CreativeWorker"), "Should contain CreativeWorker sub-agent.");
        assertTrue(subAgentNames.contains("AnalyticalWorker"), "Should contain AnalyticalWorker sub-agent.");
        assertEquals(2, subAgentNames.size(), "Should have exactly two sub-agents.");
    }

    @Test
    void rootAgent_subAgentsShouldAlsoUseTestModel() {
        Agent coordinator = CoordinatorAgent.ROOT_AGENT;
        for (Agent subAgent : coordinator.subAgents()) {
            assertTrue(subAgent instanceof LlmAgent, "Sub-agent should be an LlmAgent.");
            LlmAgent llmSubAgent = (LlmAgent) subAgent;
            assertEquals("test-model-from-properties", llmSubAgent.model().name(), 
                         "Sub-agent " + subAgent.name() + " model name should match test config.");
        }
    }
}
