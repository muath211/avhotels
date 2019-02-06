package com.avhotels.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {

    private String provider;

    private String name;

    private double fare;

    private List<String> amenities;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void addAdditionalProperty(String key, Object value) {
        this.additionalProperties.put(key, value);
    }
}
