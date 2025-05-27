package com.example.multiagent;

import com.google.adk.web.AdkWebServer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting ADK WebServer for Multi-Agent System...");
        System.out.println("Ensure your GOOGLE_API_KEY is set as an environment variable.");
        System.out.println("Access the Dev UI at http://localhost:8080 (or the configured port).");
        System.out.println("The CoordinatorAgent should be available in the agent dropdown.");

        // ADK WebServer will automatically find any agents declared as
        // public static Agent ROOT_AGENT in classes within the source path.
        // The exec-maven-plugin in pom.xml is already configured to pass
        // --adk.agents.source-dir=src/main/java
        // and to run com.google.adk.web.AdkWebServer.
        // So, we just need to call its main method if we were to run this directly,
        // but typically this main will be invoked by `mvn exec:java`.
        // For clarity, we can still call it here, or simply leave a message.
        // Let's call it, so `java -cp ... com.example.multiagent.Main` would also work if classpath is set up.

        try {
            AdkWebServer.main(args); // Pass through any command-line arguments
        } catch (Exception e) {
            System.err.println("Failed to start AdkWebServer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
