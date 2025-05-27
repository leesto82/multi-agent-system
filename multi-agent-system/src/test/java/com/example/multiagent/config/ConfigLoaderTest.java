package com.example.multiagent.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    @Test
    void getModelName_shouldReturnModelNameFromTestProperties() {
        assertEquals("test-model-from-properties", ConfigLoader.getModelName());
    }

    @Test
    void getApiKey_shouldReturnApiKeyFromTestProperties() {
        assertEquals("test_api_key_12345", ConfigLoader.getApiKey());
    }

    @Test
    void getApiBase_shouldReturnApiBaseFromTestProperties() {
        assertEquals("https://test.api.base/v1", ConfigLoader.getApiBase());
    }

    @Test
    void getSslVerify_shouldReturnSslVerifyFromTestProperties() {
        assertTrue(ConfigLoader.getSslVerify());
    }
}
