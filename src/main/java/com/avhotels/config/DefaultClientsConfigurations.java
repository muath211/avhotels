package com.avhotels.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultClientsConfigurations implements ClientsConfigurations {

    @Value("${hotels.crazy.name}")
    private String crazyHotelsProvider;

    @Value("${hotels.best.name}")
    private String bestHotelsProvider;

    @Override
    public String getCrazyHotelsProvider() {
        return crazyHotelsProvider;
    }

    public void setCrazyHotelsProvider(String crazyHotelsProvider) {
        this.crazyHotelsProvider = crazyHotelsProvider;
    }

    @Override
    public String getBestHotelsProvider() {
        return bestHotelsProvider;
    }

    public void setBestHotelsProvider(String bestHotelsProvider) {
        this.bestHotelsProvider = bestHotelsProvider;
    }
}
