package com.avhotels.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultEndpoints implements Endpoints {

    @Value("${hotels.crazy.url}")
    private String crazyHotelUrl;

    @Value("${hotels.best.url}")
    private String bestHotelUrl;

    public String getCrazyHotelUrl() {
        return crazyHotelUrl;
    }

    public void setCrazyHotelUrl(String crazyHotelUrl) {
        this.crazyHotelUrl = crazyHotelUrl;
    }

    public String getBestHotelUrl() {
        return bestHotelUrl;
    }

    public void setBestHotelUrl(String bestHotelUrl) {
        this.bestHotelUrl = bestHotelUrl;
    }

}
