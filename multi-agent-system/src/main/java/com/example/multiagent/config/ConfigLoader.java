package com.example.multiagent.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final String CONFIG_FILE = "llm_config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Sorry, unable to find " + CONFIG_FILE);
                // Consider throwing a runtime exception or handling this more gracefully
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            // Log the exception, or throw a runtime exception
            System.err.println("Error loading configuration file " + CONFIG_FILE + ": " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static String getModelName() {
        return properties.getProperty("llm.model_name", "gemini-1.5-pro-latest"); // Default if not found
    }

    public static String getApiKey() {
        return properties.getProperty("llm.api_key");
    }

    public static String getApiBase() {
        return properties.getProperty("llm.api_base");
    }

    public static boolean getSslVerify() {
        return Boolean.parseBoolean(properties.getProperty("llm.ssl_verify", "true")); // Default to true
    }

    // Main method for testing the loader (optional)
    public static void main(String[] args) {
        System.out.println("Model Name: " + getModelName());
        System.out.println("API Key: " + getApiKey());
        System.out.println("API Base: " + getApiBase());
        System.out.println("SSL Verify: " + getSslVerify());
    }
}
