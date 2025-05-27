# Multi-Agent System (ADK-Java)

This project implements a multi-agent system using Google's Agent Development Kit (ADK) for Java. It features a coordinator agent that delegates tasks to specialized worker agents. Agent behavior, including the LLM model used, is configured via a properties file.

## Prerequisites

*   Java Development Kit (JDK) 17 or higher
*   Apache Maven

## Configuration (`llm_config.properties`)

Before running the application, you **MUST** configure your LLM settings in `multi-agent-system/src/main/resources/llm_config.properties`.

Create this file if it doesn't exist, or modify the existing one with the following properties:

```properties
# LLM Configuration

# Model name for the agents.
# For Google's Gemini models, use names like "gemini-1.5-pro-latest", "gemini-1.5-flash-latest", etc.
# For your self-hosted model, set to: hosted_vllm/DeepSeek-R1-108
llm.model_name=hosted_vllm/DeepSeek-R1-108

# API Key for the LLM.
# For Gemini models, this is your Google AI Studio API Key.
# For your self-hosted model, set to: sk-pcd6da59ATz77s4O30C23dC5Fc574320B37f8aA21f58637d
llm.api_key=sk-pcd6da59ATz77s4O30C23dC5Fc574320B37f8aA21f58637d

# API Base URL for the LLM.
# For your self-hosted model, set to: https://oneapi.rnd.huawei.com/v1
# IMPORTANT NOTE: While this setting is loaded, ADK-Java (as of current documentation)
# primarily supports Gemini and Anthropic models directly. It does not offer a straightforward
# documented method to force LlmAgent.builder() to use a custom api_base for arbitrary
# model_name strings. This setting is provided for completeness but may not be
# automatically applied by ADK for your custom model endpoint.
llm.api_base=https://oneapi.rnd.huawei.com/v1

# SSL Verification for LLM calls.
# Set to false to attempt to disable SSL verification, e.g., for local endpoints with self-signed certificates.
# IMPORTANT NOTE: Similar to api_base, ADK-Java does not offer a documented, direct way
# to disable SSL verification for the HTTP client used by LlmAgent.builder() for arbitrary models.
# This setting is provided for completeness but may not be automatically applied by ADK.
llm.ssl_verify=false
```

**Environment Variables for Standard Providers:**
*   If using Google Gemini models, ensure `GOOGLE_API_KEY` is set with your API key (this can be the same as `llm.api_key`).
*   (Optional) `GOOGLE_GENAI_USE_VERTEXAI=FALSE`: Set this if using Gemini API keys directly.

ADK-Java might require specific environment variables for API key authentication with its supported model providers (Gemini, Anthropic), even if an API key is specified in the properties file, as the direct programmatic application of the key to the underlying client for arbitrary models is not fully clear from the `LlmAgent.builder()` interface alone.

## Architecture

The system consists of agents located in `multi-agent-system/src/main/java/com/example/multiagent/`:
*   **`CoordinatorAgent.java`**: The `ROOT_AGENT`, an `LlmAgent` that delegates tasks.
*   **Worker Agents** (`worker/`):
    *   `CreativeWorkerAgent.java`: `LlmAgent` for creative tasks.
    *   `AnalyticalWorkerAgent.java`: `LlmAgent` for analytical tasks.
All agents load their `model_name` from `llm_config.properties`.

## Project Structure

*   `multi-agent-system/pom.xml`: Maven project file.
*   `multi-agent-system/src/main/resources/llm_config.properties`: LLM configuration.
*   `multi-agent-system/src/main/java/com/example/multiagent/config/ConfigLoader.java`: Loads the properties.
*   `multi-agent-system/src/main/java/com/example/multiagent/Main.java`: Launches the ADK WebServer.
*   Agent classes in `com.example.multiagent.*` and `com.example.multiagent.worker.*`.

## Building the Project

1.  Navigate to `multi-agent-system`: `cd multi-agent-system`
2.  Build with Maven: `mvn clean package`

## Running the Multi-Agent System

1.  **Configure `llm_config.properties`** as described above.
2.  Set any necessary environment variables (e.g., `GOOGLE_API_KEY` if also using Gemini, or if your custom endpoint relies on it via some ADK passthrough).
3.  Navigate to `multi-agent-system`.
4.  Run the ADK WebServer: `mvn exec:java`
5.  Open your browser to `http://localhost:8080`.
6.  Select "MasterCoordinatorAgent" and interact.

**Note on Custom Endpoints (like `hosted_vllm/DeepSeek-R1-108`):**
As mentioned in the configuration section, ADK-Java's `LlmAgent` is primarily documented for Gemini and Anthropic models. While the system will use your specified `llm.model_name`, the application of custom `llm.api_base` and `llm.ssl_verify=false` from the properties file to the underlying HTTP client used by ADK is **not guaranteed** by current ADK-Java documentation for arbitrary model providers. The behavior might depend on how ADK handles unknown model strings or if it falls back to other global HTTP client configurations. For Python ADK, `LiteLLM` provides explicit support for these custom parameters. This functionality is not clearly exposed in Java ADK's public API for `LlmAgent`.

## Running Tests

Unit tests have been added to verify configuration loading and agent instantiation.
To run the tests, navigate to the `multi-agent-system` directory and use Maven:

```bash
cd multi-agent-system
mvn test
```

This command will execute all unit tests located in `src/test/java/`.
