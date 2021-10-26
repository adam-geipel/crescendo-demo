package com.ageipel.crescendo.demo.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// Configure this with an application.yaml file (ommitted for privacy)
@Configuration
@ConfigurationProperties(prefix = "yelp.authorization")
public class YelpConfigurationProperties {

    public String apiKey;
    public String clientId;
    public String baseUrl;

    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String authToken) {
        this.apiKey = authToken;
    }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
}
